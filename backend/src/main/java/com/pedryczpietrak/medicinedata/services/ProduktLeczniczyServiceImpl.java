package com.pedryczpietrak.medicinedata.services;

import com.pedryczpietrak.medicinedata.exceptions.EmptyPageException;
import com.pedryczpietrak.medicinedata.model.DTO.CountResult;
import com.pedryczpietrak.medicinedata.model.DTO.ProduktLeczniczyDTO;
import com.pedryczpietrak.medicinedata.model.mappers.ProduktLeczniczyDTOMapper;
import com.pedryczpietrak.medicinedata.repositories.ProduktLeczniczyRepository;
import com.pedryczpietrak.medicinedata.services.interfaces.ProduktLeczniczyService;
import jakarta.persistence.Tuple;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.lang.model.element.Element;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProduktLeczniczyServiceImpl implements ProduktLeczniczyService {

    ProduktLeczniczyRepository repository;
    ProduktLeczniczyDTOMapper mapper;

    @Autowired
    public ProduktLeczniczyServiceImpl(ProduktLeczniczyRepository repository) {
        this.repository = repository;
        this.mapper = Mappers.getMapper(ProduktLeczniczyDTOMapper.class);
    }

    @Override
    public Page<ProduktLeczniczyDTO> getAllProduktLeczniczyPage(int page, String sortBy, boolean isAscending) {
        Page<ProduktLeczniczyDTO> produkty;
        if (isAscending) produkty = repository.findAllBy(PageRequest.of(page, 20, Sort.by(sortBy).ascending()))
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
        if (isAscending) produkty = repository.findAllByNazwaProduktuContainingIgnoreCase(name, PageRequest.of(page, 20, Sort.by(sortBy).ascending()))
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
        List<Map<String, Long>> response = repository.countSubstancjaCzynnaTop10();
        if(response.isEmpty()) throw new NullPointerException();
        List<CountResult> results = new ArrayList<>();
        for(Map<String, Long> res: response){
            int i = 0;
            results.add(new CountResult(res.get("substancja_czynna") + "", res.get("count")));
        }
        return results;
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
}
