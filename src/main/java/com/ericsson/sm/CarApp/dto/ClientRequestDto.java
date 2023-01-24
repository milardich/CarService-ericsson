package com.ericsson.sm.CarApp.dto;

import lombok.Getter;
import lombok.Setter;

public class ClientRequestDto {
    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String oib;

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String street;

    @Getter
    @Setter
    private String number;

    @Getter
    @Setter
    private String zipCode;

    @Getter
    @Setter
    private String country;


}
