package com.pedryczpietrak.medicinedata.controllers;

import com.pedryczpietrak.medicinedata.model.DTO.ProduktLeczniczyDTO;
import com.pedryczpietrak.medicinedata.services.interfaces.ProduktLeczniczyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produkt")
public class ProduktLeczniczyController {
    private final ProduktLeczniczyService produktLeczniczyService;

    @Autowired
    public ProduktLeczniczyController(ProduktLeczniczyService produktLeczniczyService) {
        this.produktLeczniczyService = produktLeczniczyService;
    }

    @GetMapping("/all/{sortBy}/{isAscending}/{page}")
    public ResponseEntity<Page<ProduktLeczniczyDTO>> getProduktLeczniczyPage(@PathVariable String sortBy,
                                                                             @PathVariable boolean isAscending,
                                                                             @PathVariable int page){
        return new ResponseEntity<>(produktLeczniczyService.getAllProduktLeczniczyPage(page, sortBy, isAscending), HttpStatus.OK);
    }
}
