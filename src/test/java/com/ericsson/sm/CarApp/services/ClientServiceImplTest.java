package com.ericsson.sm.CarApp.services;

import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.impl.ClientServiceImpl;
import com.ericsson.sm.CarApp.service.mapper.ClientMapper;
import com.ericsson.sm.CarApp.validation.ClientValidation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientValidation clientValidation;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveClient_returnOkWithClientResponse(){

        ClientRequestDto expectedClientRequestDto = new ClientRequestDto();
        expectedClientRequestDto.setOib("11122233344");
        expectedClientRequestDto.setEmail("test@test.com");
        expectedClientRequestDto.setFirstName("testFirstName");
        expectedClientRequestDto.setLastName("testLastName");
        expectedClientRequestDto.setCity("Osijek");
        expectedClientRequestDto.setCountry("Croatia");
        expectedClientRequestDto.setNumber("99a");
        expectedClientRequestDto.setZipCode("31000");
        expectedClientRequestDto.setStreet("TestUlica");

        Client client = new Client();
        client.setId(5L);
        client.setOib("11122233344");

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setOib("11122233344");
        clientResponseDto.setEmail("test@test.com");
        clientResponseDto.setFirstName("testFirstName");
        clientResponseDto.setLastName("testLastName");
        clientResponseDto.setCity("Osijek");
        clientResponseDto.setCountry("Croatia");
        clientResponseDto.setNumber("99a");
        clientResponseDto.setZipCode("31000");
        clientResponseDto.setStreet("TestUlica");

        Mockito.when(clientMapper.toEntity(Mockito.any(ClientRequestDto.class))).thenReturn(client);
        Mockito.doNothing().when(clientValidation).validate(Mockito.any(Client.class));
        Mockito.when(clientRepository.save(Mockito.any(Client.class))).thenReturn(client);
        Mockito.when(clientMapper.toDto(Mockito.any(Client.class))).thenReturn(clientResponseDto);

        ClientResponseDto actualSavedClientResponse = clientService.save(expectedClientRequestDto);

        Assert.assertEquals(expectedClientRequestDto.getOib(), actualSavedClientResponse.getOib());
    }

    @Test
    void testFindClientById_returnOkWithClientResponse(){
        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setOib("12345678910");

        Client client = new Client();
        client.setId(55L);
        client.setOib("12345678910");

        Mockito.doNothing().when(clientValidation).existsById(Mockito.anyLong());
        Mockito.when(clientRepository.getReferenceById(Mockito.anyLong())).thenReturn(client);
        Mockito.when(clientMapper.toDto(Mockito.any(Client.class))).thenReturn(clientResponseDto);

        ClientResponseDto actualClient = clientService.findById(55L);

        Assert.assertEquals(client.getOib(), actualClient.getOib());
    }

    @Test
    void testUpdateClientById_returnOkWithClientResponse(){

        ClientRequestDto clientRequestDto = new ClientRequestDto();
        clientRequestDto.setOib("11122233344");
        clientRequestDto.setEmail("test@test.com");
        clientRequestDto.setFirstName("testFirstName");
        clientRequestDto.setLastName("testLastName");
        clientRequestDto.setCity("Osijek");
        clientRequestDto.setCountry("Croatia");
        clientRequestDto.setNumber("99a");
        clientRequestDto.setZipCode("31000");
        clientRequestDto.setStreet("TestUlica");

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setOib("11122233344");
        clientResponseDto.setEmail("test@test.com");
        clientResponseDto.setFirstName("testFirstName");
        clientResponseDto.setLastName("testLastName");
        clientResponseDto.setCity("Osijek");
        clientResponseDto.setCountry("Croatia");
        clientResponseDto.setNumber("99a");
        clientResponseDto.setZipCode("31000");
        clientResponseDto.setStreet("TestUlica");
        clientResponseDto.setCars(new ArrayList<>());

        Client client = new Client();
        client.setId(55L);
        client.setOib("11122233344");
        client.setCars(new ArrayList<>());

        Mockito.doNothing().when(clientValidation).existsById(Mockito.anyLong());
        Mockito.when(clientRepository.getReferenceById(Mockito.anyLong())).thenReturn(client);
        Mockito.when(clientMapper.toEntity(Mockito.any(Client.class), Mockito.any(ClientRequestDto.class))).thenReturn(client);
        Mockito.when(clientRepository.save(Mockito.any(Client.class))).thenReturn(client);
        Mockito.when(clientMapper.toDto(Mockito.any(Client.class))).thenReturn(clientResponseDto);

        ClientResponseDto actualClientResponse = clientService.updateById(55L, clientRequestDto);

        Assert.assertEquals(clientRequestDto.getOib(), actualClientResponse.getOib());
        Assert.assertEquals(clientRequestDto.getEmail(), actualClientResponse.getEmail());
        Assert.assertEquals(clientRequestDto.getCity(), actualClientResponse.getCity());
        Assert.assertEquals(clientRequestDto.getCountry(), actualClientResponse.getCountry());
        Assert.assertEquals(clientRequestDto.getNumber(), actualClientResponse.getNumber());
        Assert.assertEquals(clientRequestDto.getFirstName(), actualClientResponse.getFirstName());
        Assert.assertEquals(clientRequestDto.getLastName(), actualClientResponse.getLastName());
        Assert.assertEquals(clientRequestDto.getStreet(), actualClientResponse.getStreet());
        Assert.assertEquals(clientRequestDto.getZipCode(), actualClientResponse.getZipCode());
    }
}
