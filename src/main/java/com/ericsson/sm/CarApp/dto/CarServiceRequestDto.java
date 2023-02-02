package com.ericsson.sm.CarApp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CarServiceRequestDto {
    //private LocalDateTime dateOfService;
    private String workerFirstName;
    private String workerLastName;
    private String workDescription;
    private Float price;
    private boolean isPaid;
}
