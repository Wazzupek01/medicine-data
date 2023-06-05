package com.pedryczpietrak.medicinedata.controllers;

import com.pedryczpietrak.medicinedata.exceptions.ErrorResponse;
import com.pedryczpietrak.medicinedata.model.DTO.AuthenticationResponseDTO;
import com.pedryczpietrak.medicinedata.model.DTO.CountResult;
import com.pedryczpietrak.medicinedata.model.DTO.GeneratedFileParams;
import com.pedryczpietrak.medicinedata.model.DTO.ProduktLeczniczyDTO;
import com.pedryczpietrak.medicinedata.services.interfaces.ProduktLeczniczyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/name/{name}/{sortBy}/{isAscending}/{page}")
    @SecurityRequirement(name = "Bearer authentication")
    @Operation(summary = "Get by name", description = "Get page of sorted produktLeczniczy filtered by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponseDTO.class))),
            @ApiResponse(responseCode = "403", description = "Page does not exist or user not logged in",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Page<ProduktLeczniczyDTO>> getProduktLeczniczyPage(@PathVariable String name,
                                                                             @PathVariable String sortBy,
                                                                             @PathVariable boolean isAscending,
                                                                             @PathVariable int page) {
        return new ResponseEntity<>(produktLeczniczyService.getProduktLeczniczyByNamePage(name, page, sortBy, isAscending), HttpStatus.OK);
    }

    @GetMapping("/params")
    @SecurityRequirement(name = "Bearer authentication")
    @Operation(summary = "Get sort params", description = "Returns all possible parameters for sorting")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parameters returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = String.class)))),
            @ApiResponse(responseCode = "403", description = "User is not logged in",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<String>> listAllSortParameters() {
        return new ResponseEntity<>(produktLeczniczyService.listAllSortParameters(), HttpStatus.OK);
    }

    @GetMapping("/topsubstancje")
    @SecurityRequirement(name = "Bearer authentication")
    @Operation(summary = "Top substancjaLecznicza count", description = "Returns top 10 most common substancjaLecznicza counted")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parameters returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CountResult.class)))),
            @ApiResponse(responseCode = "404", description = "No substancjaLecznica found"),
            @ApiResponse(responseCode = "403", description = "User not logged in")
    })
    public ResponseEntity<List<CountResult>> getTop10SubstancjaLecznicza(){
        return new ResponseEntity<>(produktLeczniczyService.getSubstancjaCzynnaCountTop10(), HttpStatus.OK);
    }

    @GetMapping("/toppostaci")
    @SecurityRequirement(name = "Bearer authentication")
    @Operation(summary = "Top postac count", description = "Returns top 10 most common postac counted")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parameters returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CountResult.class)))),
            @ApiResponse(responseCode = "404", description = "No produktLeczniczy found"),
            @ApiResponse(responseCode = "403", description = "User not logged in")
    })
    public ResponseEntity<List<CountResult>> getTop10Postac(){
        return new ResponseEntity<>(produktLeczniczyService.getPostacCountTop10(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @SecurityRequirement(name = "Bearer authentication")
    @Operation(summary = "Delete produktLeczniczy", description = "Delete produktLeczniczy by id if user has role 'ADMIN'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Removed",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CountResult.class)))),
            @ApiResponse(responseCode = "403", description = "User does not have permission to delete")
    })
    public ResponseEntity<HttpStatus> deleteProduktLeczniczyById(@PathVariable Integer id){
        produktLeczniczyService.deleteProduktLeczniczyById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/xml", produces = "text/xml")
    @SecurityRequirement(name = "Bearer authentication")
    @Operation(summary = "Get xml file", description = "Generate xml file with specified sorting and order, and without specified fields")
    @ApiResponse(responseCode = "200", description = "generated file", content = @Content(mediaType = "application/xml"))
    public ResponseEntity<byte []> getProduktLeczniczyXmlFile(@RequestBody GeneratedFileParams params){
        return new ResponseEntity<>(produktLeczniczyService.getProduktLeczniczyXmlFile(params), HttpStatus.OK);
    }

    @PostMapping(value = "/json", produces = "application/json")
    @SecurityRequirement(name = "Bearer authentication")
    @Operation(summary = "Get json file", description = "Generate json file with specified sorting and order, and without specified fields")
    @ApiResponse(responseCode = "200", description = "generated file", content = @Content(mediaType = "application/json"))
    public ResponseEntity<byte []> getProduktLeczniczyJsonFile(@RequestBody GeneratedFileParams params){
        return new ResponseEntity<>(produktLeczniczyService.getProduktLeczniczyJsonFile(params), HttpStatus.OK);
    }
}
