package com.pedryczpietrak.medicinedata.services;

import com.pedryczpietrak.medicinedata.exceptions.EmptyPageException;
import com.pedryczpietrak.medicinedata.model.DTO.ProduktLeczniczyDTO;
import com.pedryczpietrak.medicinedata.model.mappers.ProduktLeczniczyDTOMapper;
import com.pedryczpietrak.medicinedata.repositories.ProduktLeczniczyRepository;
import com.pedryczpietrak.medicinedata.services.interfaces.ProduktLeczniczyService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
        Page<ProduktLeczniczyDTO> produkty = null;
        if (isAscending) produkty = repository.findAllBy(PageRequest.of(page, 20, Sort.by(sortBy).ascending()))
                .map(mapper::produktLeczniczyToProduktLeczniczyDTO);
        else produkty = repository.findAllBy(PageRequest.of(page, 20, Sort.by(sortBy).descending()))
                .map(mapper::produktLeczniczyToProduktLeczniczyDTO);

        if (produkty != null && produkty.hasContent()) {
            return produkty;
        } else {
            throw new EmptyPageException();
        }
    }
}
