package com.ericsson.sm.CarApp.service.impl;

import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public ClientResponseDto save(ClientRequestDto clientRequestDto) {
        Client client = new Client();
        client.setFirstName(clientRequestDto.getFirstName());
        client.setLastName(clientRequestDto.getLastName());
        client.setOib(clientRequestDto.getOib());
        client.setCity(clientRequestDto.getCity());
        client.setStreet(clientRequestDto.getStreet());
        client.setZipCode(clientRequestDto.getZipCode());
        client.setCountry(clientRequestDto.getCountry());
        //client.setNumber(clientRequestDto.getNumber());

        Client savedClient = clientRepository.save(client);

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setFirstName(savedClient.getFirstName());
        clientResponseDto.setLastName(savedClient.getLastName());
        clientResponseDto.setOib(savedClient.getOib());
        clientResponseDto.setCity(savedClient.getCity());
        clientResponseDto.setStreet(savedClient.getStreet());
        clientResponseDto.setZipCode(savedClient.getZipCode());
        clientResponseDto.setCountry(savedClient.getCountry());
        //clientResponseDto.setNumber(savedClient.getNumber());

        return clientResponseDto;
    }
}
