package com.ericsson.sm.CarApp.service.impl;

import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.ClientService;
import com.ericsson.sm.CarApp.service.mapper.ClientDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientDtoMapper clientDtoMapper;

    @Override
    public ClientResponseDto save(ClientRequestDto clientRequestDto) {
        Client client = clientDtoMapper.toEntity(clientRequestDto);
        Client savedClient = clientRepository.save(client);
        return clientDtoMapper.toDto(savedClient);
    }

    @Override
    public Page<ClientResponseDto> getAll(String firstName, String lastName, Pageable pageable) {
        return clientRepository.findByFirstOrLastName_sortedByLastNameASC(
                firstName, lastName, pageable
        ).map(clientDtoMapper::toDto);
    }

    @Override
    public ClientResponseDto findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Client with id " + id + " not found")
        );
        ClientResponseDto clientResponseDto = new ClientResponseDto();
        if(client != null){
            clientResponseDto = clientDtoMapper.toDto(client);
        }
        return clientResponseDto;
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Client with id " + id + " not found")
        );
        if(client != null){
            clientRepository.deleteById(id);
        }
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
