package com.example.uber.adapters.web.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodTruckLocationDTO {

    private double latitude;
    private double longitude;
    private String member;

    public FoodTruckLocationDTO(double latitude, double longitude, String member){
        this.latitude = latitude;
        this.longitude = longitude;
        this.member = member;
    }
}
