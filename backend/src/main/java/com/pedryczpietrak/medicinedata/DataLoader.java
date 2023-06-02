package com.pedryczpietrak.medicinedata;

import com.pedryczpietrak.medicinedata.model.DTO.UserRegisterDTO;
import com.pedryczpietrak.medicinedata.model.entities.Role;
import com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy.Opakowania;
import com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy.ProduktLeczniczy;
import com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy.ProduktyLecznicze;
import com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy.SubstancjeCzynne;
import com.pedryczpietrak.medicinedata.repositories.ProduktLeczniczyRepository;
import com.pedryczpietrak.medicinedata.repositories.RoleRepository;
import com.pedryczpietrak.medicinedata.repositories.UserRepository;
import com.pedryczpietrak.medicinedata.services.AuthenticationService;
import me.tongfei.progressbar.ProgressBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import java.io.FileReader;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

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
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            roleRepository.saveAll(List.of(new Role(1, "ADMIN"), new Role(0, "USER")));
            authenticationService.register(new UserRegisterDTO("Admin!01", "admin@admin.pl", roleRepository.findRoleByName("ADMIN")));
            authenticationService.register(new UserRegisterDTO("User1!01", "user1@user.pl", roleRepository.findRoleByName("USER")));
        }

        if (produktLeczniczyRepository.count() > 0) return;

        System.out.println(System.getProperty("user.dir"));
        JAXBContext context = JAXBContext.newInstance(ProduktyLecznicze.class);
        ProduktyLecznicze produktyLecznicze = (ProduktyLecznicze) context.createUnmarshaller()
                .unmarshal(new FileReader("./src/main/resources/data.xml"));
        try (ProgressBar pb = new ProgressBar("Loading data", produktyLecznicze.getProduktyLecznicze().size())) {
            for (ProduktLeczniczy p : produktyLecznicze.getProduktyLecznicze()) {
                Opakowania opakowania = p.getOpakowania();
                SubstancjeCzynne substancjeCzynne = p.getSubstancjeCzynne();
                if (opakowania.getOpakowania() != null)
                    opakowania.getOpakowania().forEach((o) -> o.setOpakowania(opakowania));

                if (substancjeCzynne.getSubstancjeCzynne() != null)
                    substancjeCzynne.getSubstancjeCzynne().forEach((s) -> s.setSubstancjeCzynne(substancjeCzynne));
                produktLeczniczyRepository.save(p);
                pb.step();
                pb.setExtraMessage("Loading...");
            }
        }
        System.out.println("Data loaded successfully!");
    }
}
