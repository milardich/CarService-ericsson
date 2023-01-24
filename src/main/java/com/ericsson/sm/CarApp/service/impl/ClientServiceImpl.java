package com.ericsson.sm.CarApp.service.impl;

import com.ericsson.sm.CarApp.dto.AllClientsResponseDto;
import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

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
        client.setNumber(clientRequestDto.getNumber());

        Client savedClient = clientRepository.save(client);

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setFirstName(savedClient.getFirstName());
        clientResponseDto.setLastName(savedClient.getLastName());
        clientResponseDto.setOib(savedClient.getOib());
        clientResponseDto.setCity(savedClient.getCity());
        clientResponseDto.setStreet(savedClient.getStreet());
        clientResponseDto.setZipCode(savedClient.getZipCode());
        clientResponseDto.setCountry(savedClient.getCountry());
        clientResponseDto.setNumber(savedClient.getNumber());

        return clientResponseDto;
    }

    @Override
    public List<AllClientsResponseDto> getAll() {
        List<Client> allClients = clientRepository.findAll();
        List<AllClientsResponseDto> savedClients = new ArrayList<>();
        for(Client client : allClients){
            AllClientsResponseDto allClientsResponseDto = new AllClientsResponseDto();
            allClientsResponseDto.setFirstName(client.getFirstName());
            allClientsResponseDto.setLastName(client.getLastName());
            allClientsResponseDto.setOib(client.getOib());
            allClientsResponseDto.setCity(client.getCity());
            allClientsResponseDto.setStreet(client.getStreet());
            allClientsResponseDto.setNumber(client.getNumber());
            allClientsResponseDto.setZipCode(client.getZipCode());
            allClientsResponseDto.setCountry(client.getCountry());
            savedClients.add(allClientsResponseDto);
        }
        return savedClients;
    }

    @Override
    public ClientResponseDto findById(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        ClientResponseDto clientResponseDto = new ClientResponseDto();
        if(client != null){
            clientResponseDto.setFirstName(client.getFirstName());
            clientResponseDto.setLastName(client.getLastName());
            clientResponseDto.setOib(client.getOib());
            clientResponseDto.setCity(client.getCity());
            clientResponseDto.setStreet(client.getStreet());
            clientResponseDto.setNumber(client.getNumber());
            clientResponseDto.setZipCode(client.getZipCode());
            clientResponseDto.setCountry(client.getCountry());
        }
        return clientResponseDto;
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        if(client == null){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        clientRepository.deleteById(id);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

    @Override
    public ClientResponseDto updateById(Long id, ClientRequestDto clientRequestDto) {
        Client client = clientRepository.findById(id).orElse(null);

        client.setFirstName(clientRequestDto.getFirstName());
        client.setLastName(clientRequestDto.getLastName());
        client.setOib(clientRequestDto.getOib());
        client.setCity(clientRequestDto.getCity());
        client.setStreet(clientRequestDto.getStreet());
        client.setNumber(clientRequestDto.getNumber());
        client.setZipCode(clientRequestDto.getZipCode());
        client.setCountry(clientRequestDto.getCountry());

        Client savedClient = clientRepository.save(client);

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setFirstName(savedClient.getFirstName());
        clientResponseDto.setLastName(savedClient.getLastName());
        clientResponseDto.setOib(savedClient.getOib());
        clientResponseDto.setCity(savedClient.getCity());
        clientResponseDto.setStreet(savedClient.getStreet());
        clientResponseDto.setNumber(savedClient.getNumber());
        clientResponseDto.setZipCode(savedClient.getZipCode());
        clientResponseDto.setCountry(savedClient.getCountry());

        return clientResponseDto;
    }
}
