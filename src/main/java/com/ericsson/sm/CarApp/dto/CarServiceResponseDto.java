package com.ericsson.sm.CarApp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class CarServiceResponseDto {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime dateOfService;
    private String workerFirstName;
    private String workerLastName;
    private String workDescription;
    private Float price;
    private boolean isPaid;
}
