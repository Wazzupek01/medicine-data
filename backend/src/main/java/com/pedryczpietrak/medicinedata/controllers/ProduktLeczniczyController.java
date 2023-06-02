package com.pedryczpietrak.medicinedata.controllers;

import com.pedryczpietrak.medicinedata.exceptions.ErrorResponse;
import com.pedryczpietrak.medicinedata.model.DTO.AuthenticationResponseDTO;
import com.pedryczpietrak.medicinedata.model.DTO.ProduktLeczniczyDTO;
import com.pedryczpietrak.medicinedata.services.interfaces.ProduktLeczniczyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Produkt leczniczy", description = "Getting 'produktLeczniczy' related data")
public class ProduktLeczniczyController {
    private final ProduktLeczniczyService produktLeczniczyService;

    @Autowired
    public ProduktLeczniczyController(ProduktLeczniczyService produktLeczniczyService) {
        this.produktLeczniczyService = produktLeczniczyService;
    }

    @GetMapping("/all/{sortBy}/{isAscending}/{page}")
    @SecurityRequirement(name = "Bearer authentication")
    @Operation(summary = "Get all sorted", description = "Get page of sorted produktLeczniczy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponseDTO.class))),
            @ApiResponse(responseCode = "403", description = "Page does not exist or user not logged in",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Page<ProduktLeczniczyDTO>> getProduktLeczniczyPage(@PathVariable String sortBy,
                                                                             @PathVariable boolean isAscending,
                                                                             @PathVariable int page) {
        return new ResponseEntity<>(produktLeczniczyService.getAllProduktLeczniczyPage(page, sortBy, isAscending), HttpStatus.OK);
    }
}
