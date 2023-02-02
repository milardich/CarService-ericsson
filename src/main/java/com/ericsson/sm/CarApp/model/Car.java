package com.ericsson.sm.CarApp.model;

import com.ericsson.sm.CarApp.model.enumeration.CarType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_sequence")
    @SequenceGenerator(name = "car_sequence", allocationSize = 1)
    @Column(name = "car_id")
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "car_type")
    @Enumerated
    private CarType carType;

    @Column(name = "manufacture_year")
    private Integer manufactureYear;

    @Column(name = "registration_mark")
    private String registrationMark;

    @Column(name = "color")
    private String color;

    @JsonManagedReference
    @OneToMany(mappedBy = "car")
    private List<CarService> carServices;
}
