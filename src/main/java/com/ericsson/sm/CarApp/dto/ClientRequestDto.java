package com.ericsson.sm.CarApp.dto;

import com.ericsson.sm.CarApp.model.Car;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientRequestDto {
    private String firstName;
    private String lastName;
    private String oib;
    private String city;
    private String street;
    private String number;
    private String zipCode;
    private String country;
    private List<Car> cars;
}
