package com.ericsson.sm.CarApp.service.mapper;

import com.ericsson.sm.CarApp.dto.CarServiceRequestDto;
import com.ericsson.sm.CarApp.dto.CarServiceResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.model.CarService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarServiceMapper {

    CarServiceMapper INSTANCE = Mappers.getMapper(CarServiceMapper.class);

    @Mapping(target = "dateOfService", source = "dateOfService")
    @Mapping(target = "workerFirstName", source = "workerFirstName")
    @Mapping(target = "workerLastName", source = "workerLastName")
    @Mapping(target = "workDescription", source = "workDescription")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "paid", source = "paid")
    CarService toEntity(CarServiceRequestDto carServiceRequestDto);


    @Mapping(target = "dateOfService", source = "dateOfService")
    @Mapping(target = "workerFirstName", source = "workerFirstName")
    @Mapping(target = "workerLastName", source = "workerLastName")
    @Mapping(target = "workDescription", source = "workDescription")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "paid", source = "paid")
    CarService toEntity(@MappingTarget CarService carService, CarServiceRequestDto carServiceRequestDto);


    @Mapping(target = "dateOfService", source = "dateOfService")
    @Mapping(target = "workerFirstName", source = "workerFirstName")
    @Mapping(target = "workerLastName", source = "workerLastName")
    @Mapping(target = "workDescription", source = "workDescription")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "paid", source = "paid")
    CarServiceResponseDto toDto(CarService carService);
}
