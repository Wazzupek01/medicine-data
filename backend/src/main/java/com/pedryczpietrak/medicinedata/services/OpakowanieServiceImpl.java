package com.pedryczpietrak.medicinedata.services;

import com.pedryczpietrak.medicinedata.exceptions.EmptyPageException;
import com.pedryczpietrak.medicinedata.model.DTO.CountResult;
import com.pedryczpietrak.medicinedata.model.DTO.OpakowanieDTO;
import com.pedryczpietrak.medicinedata.model.mappers.OpakowanieDTOMapper;
import com.pedryczpietrak.medicinedata.repositories.OpakowanieRepository;
import com.pedryczpietrak.medicinedata.services.interfaces.OpakowanieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class OpakowanieServiceImpl implements OpakowanieService {

    private final OpakowanieRepository opakowanieRepository;
    private final OpakowanieDTOMapper mapper;

    @Autowired
    public OpakowanieServiceImpl(OpakowanieRepository opakowanieRepository, OpakowanieDTOMapper mapper) {
        this.opakowanieRepository = opakowanieRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<OpakowanieDTO> getAllOpakowaniePage(int page, String sortBy, boolean isAscending) {
        Page<OpakowanieDTO> opakowania;
        if (sortBy.equals("null")) {
            opakowania = opakowanieRepository.findAllBy(PageRequest.of(page, 20))
                    .map(mapper::opakowanieToOpakowanieDTO);
        } else if (isAscending)
            opakowania = opakowanieRepository.findAllBy(PageRequest.of(page, 20, Sort.by(sortBy).ascending()))
                    .map(mapper::opakowanieToOpakowanieDTO);
        else opakowania = opakowanieRepository.findAllBy(PageRequest.of(page, 20, Sort.by(sortBy).descending()))
                .map(mapper::opakowanieToOpakowanieDTO);

        if (opakowania.hasContent()) {
            return opakowania;
        } else {
            throw new EmptyPageException();
        }
    }

    @Override
    public List<CountResult> countKategoriaDostepnosci() {
        List<CountResult> results = opakowanieRepository.countKategoriaDostepnosci();
        if (results.isEmpty()) throw new NullPointerException();
        return results;
    }

    @Override
    public List<String> listAllSortParameters() {
        Field[] fields = OpakowanieDTO.class.getDeclaredFields();
        List<String> fieldNames = new ArrayList<>();

        for (Field f : fields) {
            fieldNames.add(f.getName());
        }

        return fieldNames;
    }
}
