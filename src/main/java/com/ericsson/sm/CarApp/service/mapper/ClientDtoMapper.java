package com.ericsson.sm.CarApp.service.mapper;

import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientDtoMapper {

    private final ClientRepository clientRepository;
    private final CarDtoMapper carDtoMapper;

    public ClientResponseDto toDto(Client client){
        ClientResponseDto dto = new ClientResponseDto();

        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setOib(client.getOib());
        dto.setCity(client.getCity());
        dto.setStreet(client.getStreet());
        dto.setNumber(client.getNumber());
        dto.setZipCode(client.getZipCode());
        dto.setCountry(client.getCountry());

        List<CarResponseDto> cars = new ArrayList<>();
        for(Car car : client.getCars()){
            CarResponseDto carResponseDto = carDtoMapper.toDto(car);
            cars.add(carResponseDto);
        }
        dto.setCars(cars);

        return dto;
    }

    public Client toEntity(Long id, ClientRequestDto dto){
        Client client = clientRepository.findById(id).orElse(null);

        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setOib(dto.getOib());
        client.setCity(dto.getCity());
        client.setStreet(dto.getStreet());
        client.setZipCode(dto.getZipCode());
        client.setCountry(dto.getCountry());
        client.setNumber(dto.getNumber());

        return client;
    }

    public Client toEntity(ClientRequestDto dto){
        Client client = new Client();

        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setOib(dto.getOib());
        client.setCity(dto.getCity());
        client.setStreet(dto.getStreet());
        client.setZipCode(dto.getZipCode());
        client.setCountry(dto.getCountry());
        client.setNumber(dto.getNumber());

        return client;
    }
}
