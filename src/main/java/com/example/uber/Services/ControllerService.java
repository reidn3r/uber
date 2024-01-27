package com.example.uber.Services;

import com.example.uber.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.args.GeoUnit;
import redis.clients.jedis.resps.GeoRadiusResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class ControllerService {

    @Autowired
    ExternalApiService apiService;

    @Autowired
    JedisPool jedisPool;

    public ResponseEntity<List<FoodTruckLocationDTO>> findByRadius(UserLocationDTO data) throws Exception{
        if(data.radius() < 0) throw new Exception("Distância inválida");

        String key = "app:foodtruck";

        //Faz chamada para api externa
        this.apiService.redisLoadDataInMemory();

        List<FoodTruckLocationDTO> GeoFoodTrucks = FindFoodTruckByRadiusInKM(jedisPool, data);
        return ResponseEntity.status(HttpStatus.OK).body(GeoFoodTrucks);
    }

    private List<FoodTruckLocationDTO> FindFoodTruckByRadiusInKM(JedisPool jedisPool, UserLocationDTO data){
        /*
        * Encontra food truck's dentro de um determinado raio em KM base em coordenadas geográficas(lat , lon)
        */
        String key = "app:foodtruck";
        List<GeoRadiusResponse> GeoRadiusMembers;
        List<FoodTruckLocationDTO> response = new ArrayList<>();
        try(Jedis jedis = jedisPool.getResource()){
            GeoRadiusMembers =  jedis.georadius(key, data.longitude(),
                    data.latitude(), data.radius(),
                    GeoUnit.KM);

            for(GeoRadiusResponse member : GeoRadiusMembers){
                GeoCoordinate memberCoordinate = jedis.geopos(key, member.getMemberByString()).get(0);
                FoodTruckLocationDTO memberLocation = new FoodTruckLocationDTO(memberCoordinate.getLatitude(), memberCoordinate.getLongitude(), member.getMemberByString());
                response.add(memberLocation);
            }
        }
        return response;
    }
}
