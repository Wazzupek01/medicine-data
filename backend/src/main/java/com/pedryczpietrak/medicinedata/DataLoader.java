package com.pedryczpietrak.medicinedata;

import com.pedryczpietrak.medicinedata.model.ProduktyLecznicze;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import java.io.FileReader;

@Component
public class DataLoader implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println(System.getProperty("user.dir"));
        JAXBContext context = JAXBContext.newInstance(ProduktyLecznicze.class);
        ProduktyLecznicze produktyLecznicze = (ProduktyLecznicze) context.createUnmarshaller()
                .unmarshal(new FileReader("./src/main/resources/data.xml"));
        System.out.println(produktyLecznicze);
    }
}
