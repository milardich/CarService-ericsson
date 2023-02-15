package com.ericsson.sm.CarApp.controllers;

import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.service.impl.ClientServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.RequestResultMatchers;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientServiceImpl clientService;

    @Test
    void testSaveClient_returnOk() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ClientRequestDto clientRequestDto = new ClientRequestDto();
        clientRequestDto.setFirstName("Antonio");
        clientRequestDto.setLastName("Susovcek");
        clientRequestDto.setEmail("antonio@antonio.com");
        clientRequestDto.setOib("222222222");

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setFirstName(clientRequestDto.getFirstName());

        Mockito.when(clientService.save(clientRequestDto)).thenReturn(clientResponseDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    void testGetAll_returnOk() throws Exception{
        Page<ClientResponseDto> clients = new Page<>() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public <U> Page<U> map(Function<? super ClientResponseDto, ? extends U> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<ClientResponseDto> getContent() {
                return null;
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<ClientResponseDto> iterator() {
                return null;
            }
        };

        Mockito.when(clientService.getAll(Mockito.anyString(), Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(clients);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    void testFindById_returnOk() throws Exception {
        Mockito.when(clientService.findById(55L)).thenReturn(new ClientResponseDto());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/55")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    void testDeleteById_returnOk() throws Exception {
        Mockito.when(clientService.deleteById(55L)).thenReturn(ResponseEntity.ok("CLient deleted"));
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/customers/55")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    void testUpdateById_returnOk() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Mockito.when(clientService.updateById(55L, new ClientRequestDto())).thenReturn(new ClientResponseDto());
        mockMvc.perform(MockMvcRequestBuilders.put("/api/customers/55")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new ClientRequestDto())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }
}
