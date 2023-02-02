package com.ericsson.sm.CarApp.dto;

import lombok.Getter;
import lombok.Setter;

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
