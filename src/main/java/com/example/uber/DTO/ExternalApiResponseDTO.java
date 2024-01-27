package com.example.uber.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalApiResponseDTO implements Serializable {
    public long objectid;
    public String applicant;
    public String facilitytype;
    public String locationdescription;
    public String address;

    public String status;
    public String fooditems;
    public String x;
    public String y;
    public String latitude;
    public String longitude;
}
