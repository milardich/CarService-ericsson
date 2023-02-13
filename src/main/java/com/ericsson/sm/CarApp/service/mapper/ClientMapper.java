package com.ericsson.sm.CarApp.service.mapper;

import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "oib", source = "oib")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "street", source = "street")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "zipCode", source = "zipCode")
    @Mapping(target = "country", source = "country")
    @Mapping(target = "email", source = "email")
    Client toEntity(ClientRequestDto clientRequestDto);


    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "oib", source = "oib")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "street", source = "street")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "zipCode", source = "zipCode")
    @Mapping(target = "country", source = "country")
    @Mapping(target = "email", source = "email")
    Client toEntity(@MappingTarget Client client, ClientRequestDto clientRequestDto);


    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "oib", source = "oib")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "street", source = "street")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "zipCode", source = "zipCode")
    @Mapping(target = "country", source = "country")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "cars", source = "cars")
    ClientResponseDto toDto(Client client);
}
