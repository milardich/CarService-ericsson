package com.ericsson.sm.CarApp.service.impl;

import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.ClientService;
import com.ericsson.sm.CarApp.service.mapper.ClientMapper;
import com.ericsson.sm.CarApp.validation.ClientValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientValidation clientValidation;

    @Override
    public ClientResponseDto save(ClientRequestDto clientRequestDto) {
        Client client = ClientMapper.INSTANCE.toEntity(clientRequestDto);

        clientValidation.validate(client);
        client.setCars(new ArrayList<>());
        Client savedClient = clientRepository.save(client);
        return ClientMapper.INSTANCE.toDto(savedClient);
    }

    @Override
    public Page<ClientResponseDto> getAll(String firstName, String lastName, Pageable pageable) {
        return clientRepository.findByFirstOrLastName_sortedByLastNameASC(
                firstName, lastName, pageable
        ).map(ClientMapper.INSTANCE::toDto);
    }

    @Override
    public ClientResponseDto findById(Long id) {
        clientValidation.existsById(id);
        Client client = clientRepository.getReferenceById(id);
        return ClientMapper.INSTANCE.toDto(client);
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        clientValidation.existsById(id);
        clientRepository.deleteById(id);
        return new ResponseEntity<>("Client deleted", HttpStatus.OK);
    }

    @Override
    public ClientResponseDto updateById(Long id, ClientRequestDto clientRequestDto) {
        clientValidation.existsById(id);
        Client client = clientRepository.getReferenceById(id);
        client = ClientMapper.INSTANCE.toEntity(client, clientRequestDto);
        ClientResponseDto clientResponseDto;
        Client savedClient = clientRepository.save(client);
        clientResponseDto = ClientMapper.INSTANCE.toDto(savedClient);
        return clientResponseDto;
    }
}
