package com.ericsson.sm.CarApp.service.impl;

import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.ClientService;
import com.ericsson.sm.CarApp.service.mapper.ClientDtoMapper;
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
    private final ClientDtoMapper clientDtoMapper;

    @Override
    public ClientResponseDto save(ClientRequestDto clientRequestDto) {
        Client client = clientDtoMapper.toEntity((clientRequestDto));
        Client savedClient = clientRepository.save(client);
        return clientDtoMapper.toDto(savedClient);
    }

    @Override
    public List<ClientResponseDto> getAll() {
        List<Client> allClients = clientRepository.findAll();
        List<ClientResponseDto> savedClients = new ArrayList<>();
        for(Client client : allClients){
            ClientResponseDto clientResponseDto = clientDtoMapper.toDto(client);
            savedClients.add(clientResponseDto);
        }
        return savedClients;
    }

    @Override
    public ClientResponseDto findById(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        ClientResponseDto clientResponseDto = new ClientResponseDto();
        if(client != null){
            clientResponseDto = clientDtoMapper.toDto(client);
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
        Client client = clientDtoMapper.toEntity(id, clientRequestDto);
        ClientResponseDto clientResponseDto;
        Client savedClient = clientRepository.save(client);
        clientResponseDto = clientDtoMapper.toDto(savedClient);
        return clientResponseDto;
    }
}
