package com.ericsson.sm.CarApp.service.impl;

import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.ClientService;
import com.ericsson.sm.CarApp.service.mapper.ClientDtoMapper;
import com.ericsson.sm.CarApp.validation.ClientValidation;
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
        ClientValidation clientValidation = new ClientValidation();
        clientValidation.validate(client);
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
        return clientDtoMapper.toDto(client);
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        clientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Client with id " + id + " not found")
        );
        clientRepository.deleteById(id);
        return new ResponseEntity<>("Client deleted", HttpStatus.OK);
    }

    @Override
    public ClientResponseDto updateById(Long id, ClientRequestDto clientRequestDto) {
        clientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Client with id  " + id + " not found")
        );
        Client client = clientDtoMapper.toEntity(id, clientRequestDto);
        ClientResponseDto clientResponseDto;
        Client savedClient = clientRepository.save(client);
        clientResponseDto = clientDtoMapper.toDto(savedClient);
        return clientResponseDto;
    }
}
