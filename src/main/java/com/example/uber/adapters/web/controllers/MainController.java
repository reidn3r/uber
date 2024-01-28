package com.example.uber.adapters.web.controllers;

import com.example.uber.adapters.web.dtos.FoodTruckLocationDTO;
import com.example.uber.adapters.web.dtos.UserLocationDTO;
import com.example.uber.applications.usecases.services.ControllerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    ControllerService controllerService;

    @Operation(
            description = "Busca todos os foodtruck's dentro de uma determinada distância em KM a partir de uma localização dada por coordenadas geográficas (latitude, longitude) em San Francisco - CA",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sucesso. É retornado uma lista de objetos contendo coordenadas e nome "),
                    @ApiResponse(responseCode = "400", description = "Falha. Erro interno do servidor ")
            }
    )
    @GetMapping("/search")
    public ResponseEntity<List<FoodTruckLocationDTO>> findByRadius(@RequestBody @Valid UserLocationDTO data) throws Exception {
        return this.controllerService.findByRadius(data);
    }
}
