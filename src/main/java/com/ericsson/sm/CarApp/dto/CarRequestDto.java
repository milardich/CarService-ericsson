package com.ericsson.sm.CarApp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequestDto {
    private String carType;
    private Integer manufactureYear;
    private String registrationMark;
    private String color;
}
