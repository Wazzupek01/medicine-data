package com.pedryczpietrak.medicinedata.controllers;

import com.pedryczpietrak.medicinedata.exceptions.ErrorResponse;
import com.pedryczpietrak.medicinedata.model.DTO.CountResult;
import com.pedryczpietrak.medicinedata.model.DTO.OpakowanieDTO;
import com.pedryczpietrak.medicinedata.services.interfaces.OpakowanieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/opakowanie")
public class OpakowanieController {

    private final OpakowanieService opakowanieService;

    @Autowired
    public OpakowanieController(OpakowanieService opakowanieService) {
        this.opakowanieService = opakowanieService;
    }

    @GetMapping("/all/{sortBy}/{isAscending}/{page}")
    @SecurityRequirement(name = "Bearer authentication")
    @Operation(summary = "Get all sorted", description = "Get page of sorted opakowanie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "403", description = "Page does not exist or user not logged in",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Page<OpakowanieDTO>> getProduktLeczniczyPage(@PathVariable String sortBy,
                                                                       @PathVariable boolean isAscending,
                                                                       @PathVariable int page) {
        return new ResponseEntity<>(opakowanieService.getAllOpakowaniePage(page, sortBy, isAscending), HttpStatus.OK);
    }

    @GetMapping("/params")
    @SecurityRequirement(name = "Bearer authentication")
    @Operation(summary = "Get sort params", description = "Returns all possible parameters for sorting")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parameters returned",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "403", description = "User is not logged in",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<String>> listAllSortParameters(){
        return new ResponseEntity<>(opakowanieService.listAllSortParameters(), HttpStatus.OK);
    }

    @GetMapping("/kategoriedostepnosci")
    @SecurityRequirement(name = "Bearer authentication")
    @Operation(summary = "Get kategoriaDostepnosci count", description = "Returns counted appearance of each kategoriaDostepnosci")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "kategorieDostepnosci found and counted",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CountResult.class)))),
            @ApiResponse(responseCode = "404", description = "No opakowanie in database"),
            @ApiResponse(responseCode = "403", description = "User not logged in")
    })
    public ResponseEntity<List<CountResult>> countKategoriaDostepnosci(){
        return new ResponseEntity<>(opakowanieService.countKategoriaDostepnosci(), HttpStatus.OK);
    }
}
