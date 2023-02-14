package com.ericsson.sm.CarApp.service.mapper;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CarMapper {

    @Mapping(target = "carType", source = "carType")
    @Mapping(target = "manufactureYear", source = "manufactureYear")
    @Mapping(target = "registrationMark", source = "registrationMark")
    @Mapping(target = "color", source = "color")
    Car toEntity(CarRequestDto dto);


    @Mapping(target = "carType", source = "carType")
    @Mapping(target = "manufactureYear", source = "manufactureYear")
    @Mapping(target = "registrationMark", source = "registrationMark")
    @Mapping(target = "color", source = "color")
    Car toEntity(@MappingTarget Car car, CarRequestDto dto);


    @Mapping(target = "carType", source = "carType")
    @Mapping(target = "manufactureYear", source = "manufactureYear")
    @Mapping(target = "registrationMark", source = "registrationMark")
    @Mapping(target = "color", source = "color")
    @Mapping(target = "carServices", source = "carServices")
    CarResponseDto toDto(Car car);
}
