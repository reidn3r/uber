package com.example.uber.applications.usecases.services;

import com.example.uber.adapters.web.dtos.ExternalApiResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.io.IOException;
import java.util.*;

@Service
public class ExternalApiService {
    @Autowired
    RestTemplateService restTemplateService;

    @Autowired
    JedisPool jedisPool;

    public void redisLoadDataInMemory() throws Exception{
        String BaseUrl = "https://data.sfgov.org/resource/rqzj-sfat.json";
        String key = "app:foodtruck";

        try(Jedis jedis = jedisPool.getResource()){
            /*
                - Verificar se o sorted set já existe.
                    - Se sim, retorna o dado salvo em memoria.
                    - Se não, armazena tudo.
            */
            Pipeline pipeline = jedis.pipelined();
            if(!jedis.exists(key)){
                double latitude, longitude;
                String member;

                RestTemplate client = this.restTemplateService.NewRestTemplate();
                ResponseEntity<String> response = client.getForEntity(BaseUrl, String.class);

                if(!response.getStatusCode().equals(HttpStatus.OK)){
                    throw new Exception("Erro ao comunicar com API externa");
                }

                List<ExternalApiResponseDTO> body = SerializeApiResponse(response);
                for(ExternalApiResponseDTO ft : body){
                    latitude = Double.parseDouble(ft.getLatitude());
                    longitude = Double.parseDouble(ft.getLongitude());
                    member = ft.getApplicant().replace(" ", "-");

                    pipeline.geoadd(key, longitude, latitude, member);
                }
                pipeline.sync();
            }
        }
    }

    private List<ExternalApiResponseDTO> SerializeApiResponse(ResponseEntity<String> response){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ExternalApiResponseDTO[] responseBody = objectMapper.readValue(response.getBody(), ExternalApiResponseDTO[].class);
            return Arrays.asList(responseBody);
        }
        catch(IOException e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
