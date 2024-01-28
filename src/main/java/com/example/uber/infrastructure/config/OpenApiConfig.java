package com.example.uber.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Uber 2016 Challenge",
                description = "API Rest que encontra foodtrucks a partir de uma localização específica em San Franciso - CA",
                contact = @Contact(
                        name = "Reidner",
                        email = "reidnersteam@hotmail.com"
                )
        )
)

public class OpenApiConfig {
}
