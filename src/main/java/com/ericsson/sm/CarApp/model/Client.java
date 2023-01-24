package com.ericsson.sm.CarApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    @SequenceGenerator(name = "client_sequence", allocationSize = 1)
    @Column(name = "client_id")
    private Long id;

    @Column(name = "first_name")
    @Getter
    @Setter
    private String firstName;

    @Column(name = "last_name")
    @Getter
    @Setter
    private String lastName;

    @Column(name = "oib")
    @Getter
    @Setter
    private String oib;

    @Column(name = "city")
    @Getter
    @Setter
    private String city;

    @Column(name = "street")
    @Getter
    @Setter
    private String street;

    @Column(name = "street_number")
    @Getter
    @Setter
    private String number;

    @Column(name = "zip_code")
    @Getter
    @Setter
    private String zipCode;

    @Column(name = "country")
    @Getter
    @Setter
    private String country;

}
