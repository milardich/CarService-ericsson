package com.ericsson.sm.CarApp.service.mapper;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(target = "carType", source = "carType")
    @Mapping(target = "manufactureYear", source = "manufactureYear")
    @Mapping(target = "registrationMark", source = "registrationMark")
    @Mapping(target = "color", source = "color")
    Car toEntity(CarRequestDto dto);


    @Mapping(target = "carType", source = "carType")
    @Mapping(target = "manufactureYear", source = "manufactureYear")
    @Mapping(target = "registrationMark", source = "registrationMark")
    @Mapping(target = "color", source = "color")
    @Mapping(target = "carServices", source = "carServices")
    CarResponseDto toDto(Car car);
}
