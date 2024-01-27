package com.example.uber.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FoodTruckDTO implements Serializable {
    private long objectid;
    private String applicant;
    private String facilitytype;
    private String address;
    private String fooditems;
    private double x;
    private double y;

    public FoodTruckDTO(ExternalApiResponseDTO data){
        this.objectid = data.getObjectid();
        this.applicant = data.getApplicant();
        this.address = data.getAddress();
        this.fooditems = data.getFooditems();
        this.facilitytype = data.getFacilitytype();
        this.x = Double.parseDouble(data.getX());
        this.y = Double.parseDouble(data.getY());
    }

}
