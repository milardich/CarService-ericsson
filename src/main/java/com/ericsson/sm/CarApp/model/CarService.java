package com.ericsson.sm.CarApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "car_services")
@Getter
@Setter
public class CarService {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_service_sequence")
    @SequenceGenerator(name = "car_service_sequence", allocationSize = 1)
    @Column(name = "car_service_id")
    private Long carServiceId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    private LocalDateTime dateOfService;
    private String workerFirstName;
    private String workerLastName;
    private String workDescription;
    private Float price;
    private boolean isPaid;

}
