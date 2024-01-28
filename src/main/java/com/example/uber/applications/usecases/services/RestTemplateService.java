package com.example.uber.applications.usecases.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {

    public RestTemplate NewRestTemplate(){
        return new RestTemplate();
    }
}
