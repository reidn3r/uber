package com.example.uber.adapters.web.controllers;

import com.example.uber.adapters.web.dtos.FoodTruckLocationDTO;
import com.example.uber.adapters.web.dtos.UserLocationDTO;
import com.example.uber.applications.usecases.services.ControllerService;
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

    @GetMapping("/search")
    public ResponseEntity<List<FoodTruckLocationDTO>> findByRadius(@RequestBody @Valid UserLocationDTO data) throws Exception {
        return this.controllerService.findByRadius(data);
    }
}
