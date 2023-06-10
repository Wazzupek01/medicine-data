package com.pedryczpietrak.medicinedata.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedryczpietrak.medicinedata.exceptions.EmptyPageException;
import com.pedryczpietrak.medicinedata.model.DTO.CountResult;
import com.pedryczpietrak.medicinedata.model.DTO.GeneratedFileParams;
import com.pedryczpietrak.medicinedata.model.DTO.ProduktLeczniczyDTO;
import com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy.ProduktLeczniczy;
import com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy.ProduktyLecznicze;
import com.pedryczpietrak.medicinedata.model.mappers.ProduktLeczniczyDTOMapper;
import com.pedryczpietrak.medicinedata.repositories.ProduktLeczniczyRepository;
import com.pedryczpietrak.medicinedata.services.interfaces.ProduktLeczniczyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProduktLeczniczyServiceImpl implements ProduktLeczniczyService {

    private final ProduktLeczniczyRepository repository;
    private final ProduktLeczniczyDTOMapper mapper;

    @Autowired
    public ProduktLeczniczyServiceImpl(ProduktLeczniczyRepository repository, ProduktLeczniczyDTOMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<ProduktLeczniczyDTO> getAllProduktLeczniczyPage(int page, String sortBy, boolean isAscending) {
        Page<ProduktLeczniczyDTO> produkty;

        if (sortBy.equals("null")) {
            produkty = repository.findAllBy(PageRequest.of(page, 20))
                    .map(mapper::produktLeczniczyToProduktLeczniczyDTO);
        } else if (isAscending) produkty = repository.findAllBy(PageRequest.of(page, 20, Sort.by(sortBy).ascending()))
                .map(mapper::produktLeczniczyToProduktLeczniczyDTO);
        else produkty = repository.findAllBy(PageRequest.of(page, 20, Sort.by(sortBy).descending()))
                .map(mapper::produktLeczniczyToProduktLeczniczyDTO);

        if (produkty.hasContent()) {
            return produkty;
        } else {
            throw new EmptyPageException();
        }
    }

    @Override
    public Page<ProduktLeczniczyDTO> getProduktLeczniczyByNamePage(String name, int page, String sortBy, boolean isAscending) {
        Page<ProduktLeczniczyDTO> produkty;

        if (sortBy.equals("null") || sortBy.equals("")) {
            produkty = repository.findAllByNazwaProduktuContainingIgnoreCase(name, PageRequest.of(page, 20))
                    .map(mapper::produktLeczniczyToProduktLeczniczyDTO);
        } else if (isAscending) produkty = repository.findAllByNazwaProduktuContainingIgnoreCase(name, PageRequest.of(page, 20, Sort.by(sortBy).ascending()))
                .map(mapper::produktLeczniczyToProduktLeczniczyDTO);
        else produkty = repository.findAllByNazwaProduktuContainingIgnoreCase(name, PageRequest.of(page, 20, Sort.by(sortBy).descending()))
                .map(mapper::produktLeczniczyToProduktLeczniczyDTO);

        if (produkty.hasContent()) {
            return produkty;
        } else {
            throw new EmptyPageException();
        }
    }

    @Override
    public List<CountResult> getSubstancjaCzynnaCountTop10() {
        List<Object[]> response = new ArrayList<>(repository.countSubstancjaCzynnaTop10());
        if(response.isEmpty()) throw new NullPointerException();

        List<CountResult> results = new ArrayList<>();
        for(Object[] o: response){
            results.add(new CountResult((String)o[0], (Long)o[1], (Long)o[2]));
        }
        return results;
    }

    @Override
    public List<CountResult> getPostacCountTop10() {
        return repository.countPostacTop10();
    }

    @Override
    public void deleteProduktLeczniczyById(Integer id) {
        repository.deleteProduktLeczniczyById(id);
    }

    @Override
    public byte[] getProduktLeczniczyXmlFile(GeneratedFileParams params) {
        ProduktyLecznicze produktyLecznicze = new ProduktyLecznicze();
        List<ProduktLeczniczy> produkty = getProduktyLeczniczeForFile(params);
        if(params.getNullFields().size() != listAllSortParameters().size())
            nullValuesInProduktLeczniczy(produkty, params.getNullFields());
        else
            produkty = null;
        produktyLecznicze.setProduktyLecznicze(produkty);

        try {
            JAXBContext context = JAXBContext.newInstance(ProduktyLecznicze.class);
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(produktyLecznicze, new File("./result.xml"));
            return Files.readAllBytes(Paths.get("./result.xml"));
        } catch (JAXBException e){
            throw new EmptyPageException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] getProduktLeczniczyJsonFile(GeneratedFileParams params) {
        ProduktyLecznicze produktyLecznicze = new ProduktyLecznicze();
        List<ProduktLeczniczy> produkty = getProduktyLeczniczeForFile(params);

        if(params.getNullFields().size() != listAllSortParameters().size())
            nullValuesInProduktLeczniczy(produkty, params.getNullFields());
        else
            produkty = null;
        produktyLecznicze.setProduktyLecznicze(produkty);

        ObjectMapper mapperJson = new ObjectMapper();
        List<ProduktLeczniczyDTO> dto = new ArrayList<>();
        if (produkty != null) {
            for (ProduktLeczniczy p : produkty) {
                dto.add(mapper.produktLeczniczyToProduktLeczniczyDTO(p));
            }
        }
        try{
            String json = mapperJson.writeValueAsString(dto);
            Charset charset = StandardCharsets.UTF_8;
            return json.getBytes(charset);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<String> listAllSortParameters() {
        Field[] fields = ProduktLeczniczyDTO.class.getDeclaredFields();
        List<String> fieldNames = new ArrayList<>();

        for (Field f: fields) {
            fieldNames.add(f.getName());
        }

        return fieldNames;
    }

    private List<ProduktLeczniczy> getProduktyLeczniczeForFile(GeneratedFileParams params){

        List<ProduktLeczniczy> produkty;
        if(params.getSortBy() == null || params.getSortBy().equals("")){
            produkty = repository.findAllBy(PageRequest.of(0, params.getElementsNum())).getContent();
        } else {
            Sort sort = Sort.by(params.getSortBy());
            if(params.isAscending())
                sort.ascending();
            else sort.descending();
            PageRequest pageRequest = PageRequest.of(0, params.getElementsNum()).withSort(sort);
            produkty = repository.findAllBy(pageRequest).getContent();
        }
        return produkty;
    }

    private List<ProduktLeczniczy> nullValuesInProduktLeczniczy(List<ProduktLeczniczy> produkty, List<String> values){
        for(ProduktLeczniczy p: produkty){
            for(String param: values)
                switch (param.toLowerCase()) {
                    case "id" -> p.setId(null);
                    case "nazwaproduktu" -> p.setNazwaProduktu(null);
                    case "rodzajpreparatu" -> p.setRodzajPreparatu(null);
                    case "nazwapowszechniestosowana" -> p.setNazwaPowszechnieStosowana(null);
                    case "moc" -> p.setMoc(null);
                    case "postac" -> p.setPostac(null);
                    case "podmiotodpowiedzialny" -> p.setPodmiotOdpowiedzialny(null);
                    case "typprocedury" -> p.setTypProcedury(null);
                    case "numerpozwolenia" -> p.setNumerPozwolenia(null);
                    case "waznoscpozwolenia" -> p.setWaznoscPozwolenia(null);
                    case "kodatc" -> p.setKodATC(null);
                    case "opakowania" -> p.setOpakowania(null);
                    case "substancjeczynne" -> p.setSubstancjeCzynne(null);
                }
        }
        return produkty;
    }
}
