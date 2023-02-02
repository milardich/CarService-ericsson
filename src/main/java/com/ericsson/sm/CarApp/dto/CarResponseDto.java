package com.ericsson.sm.CarApp.dto;

import com.ericsson.sm.CarApp.model.enumeration.CarType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CarResponseDto {
    private CarType carType;
    private Integer manufactureYear;
    private String registrationMark;
    private String color;
    private List<CarServiceResponseDto> carServices;
}
