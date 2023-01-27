package com.ericsson.sm.CarApp.dto;

import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.model.enumeration.CarType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponseDto {
    private Long clientId;
    private CarType carType;
    private Integer manufactureYear;
    private String registrationMark;
    private String color;
}
