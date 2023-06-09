package com.pedryczpietrak.medicinedata;

import com.pedryczpietrak.medicinedata.model.DTO.UserRegisterDTO;
import com.pedryczpietrak.medicinedata.model.entities.Role;
import com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy.Opakowania;
import com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy.ProduktLeczniczy;
import com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy.ProduktyLecznicze;
import com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy.SubstancjeCzynne;
import com.pedryczpietrak.medicinedata.model.DTO.refundowane.ListaRefundacyjna;
import com.pedryczpietrak.medicinedata.model.DTO.refundowane.OpakowanieLeku;
import com.pedryczpietrak.medicinedata.repositories.ProduktLeczniczyRepository;
import com.pedryczpietrak.medicinedata.repositories.RoleRepository;
import com.pedryczpietrak.medicinedata.repositories.UserRepository;
import com.pedryczpietrak.medicinedata.services.AuthenticationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class DataLoader implements CommandLineRunner {

    // Crashes with more than 8
    private final int BATCHES_QUANTITY = 8;
    private final ProduktLeczniczyRepository produktLeczniczyRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationService authenticationService;

    @Autowired
    public DataLoader(ProduktLeczniczyRepository produktLeczniczyRepository, UserRepository userRepository,
                      RoleRepository roleRepository, AuthenticationService authenticationService) {
        this.produktLeczniczyRepository = produktLeczniczyRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationService = authenticationService;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void run(String... args) {
        long start = System.currentTimeMillis();

        if (userRepository.count() == 0) loadRolesAndUsers();
        if (produktLeczniczyRepository.count() > 0) return;

        ProduktyLecznicze produktyLecznicze = loadFromXml(ProduktyLecznicze.class, "./src/main/resources/data.xml");
        List<ProduktLeczniczy> produkty = Collections.synchronizedList(produktyLecznicze.getProduktyLecznicze());

        ListaRefundacyjna listaRefundacyjna = loadFromXml(ListaRefundacyjna.class, "./src/main/resources/refundowane.xml");
        List<OpakowanieLeku> refundowane = Collections.synchronizedList(listaRefundacyjna.getOpakowania());

        executeDataModifiers(produkty, refundowane);

        produktLeczniczyRepository.saveAll(produkty);

        System.out.println("Data loaded successfully!");
        System.out.println("Loaded in: " + ((System.currentTimeMillis() - start) / 1000.0) + "s");
    }

    private <T> T loadFromXml(Class<T> cls, String path) {
        T response = null;
        try {
            JAXBContext context = JAXBContext.newInstance(cls);
            response = (T) context.createUnmarshaller().unmarshal(new FileReader(path));
        } catch(JAXBException e){
            System.out.println("Couldn't create JAXBContext instance");
        } catch (FileNotFoundException e) {
            System.out.println("Path for file is invalid or it doesn't exist");
        }
        return response;
    }

    private void loadRolesAndUsers() {
        roleRepository.saveAll(List.of(new Role(1, "ROLE_ADMIN"), new Role(0, "ROLE_USER")));

        authenticationService.register(new UserRegisterDTO("Admin!01", "Admin!01",
                "admin@admin.pl", roleRepository.findRoleByName("ROLE_ADMIN")
                .orElseThrow(EntityNotFoundException::new).getName()));
        authenticationService.register(new UserRegisterDTO("User1!01", "User1!01",
                "user1@user.pl", roleRepository.findRoleByName("ROLE_USER").
                orElseThrow(EntityNotFoundException::new).getName()));
    }

    private void executeDataModifiers(List<ProduktLeczniczy> produkty, List<OpakowanieLeku> refundowane) {
        int batch = produkty.size() / BATCHES_QUANTITY;
        System.out.println("Loading data...");

        ExecutorService executor = Executors.newFixedThreadPool(BATCHES_QUANTITY);
        try {
            for (int i = 0; i < BATCHES_QUANTITY; i++) {
                executor.execute(new ProduktLeczniczyModifier(produkty, refundowane, i * batch, (i + 1) * batch));
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("Data processed");
    }

    static class ProduktLeczniczyModifier implements Runnable {
        List<ProduktLeczniczy> produkty;
        List<OpakowanieLeku> refundowane;
        int begin;
        int end;

        public ProduktLeczniczyModifier(List<ProduktLeczniczy> produkty, List<OpakowanieLeku> refundowane, int begin, int end) {
            this.produkty = produkty;
            this.refundowane = refundowane;
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = begin; i < end; i++) {
                ProduktLeczniczy produkt = produkty.get(i);
                Opakowania opakowania = produkt.getOpakowania();
                SubstancjeCzynne substancjeCzynne = produkt.getSubstancjeCzynne();
                if (opakowania.getOpakowania() != null)
                    opakowania.getOpakowania().forEach((o) -> {
                        o.setOpakowania(opakowania);
                        if (!produkt.isRefundowany()) {
                            boolean containsEAN = refundowane.stream().anyMatch(r -> r.getEAN().equals(o.getKodEAN()));
                            if (containsEAN) {
                                produkt.setRefundowany(true);
                            }
                        }
                    });

                if (substancjeCzynne.getSubstancjeCzynne() != null)
                    substancjeCzynne.getSubstancjeCzynne().forEach((s) -> s.setSubstancjeCzynne(substancjeCzynne));
            }
        }
    }
}
