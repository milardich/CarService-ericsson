package com.ericsson.sm.CarApp.controller;

import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/api/customers")
    public ClientResponseDto save(@RequestBody ClientRequestDto clientRequestDto){
        return clientService.save(clientRequestDto);
    }

    @GetMapping("/api/customers")
    public List<ClientResponseDto> getAll(){
        return clientService.getAll();
    }

    @GetMapping("/api/customers/{id}")
    public ClientResponseDto findById(@PathVariable Long id){
        return clientService.findById(id);
    }

    @DeleteMapping("/api/customers/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return clientService.deleteById(id);
    }

    @PutMapping("/api/customers/{id}")
    public ClientResponseDto updateById(@PathVariable Long id, @RequestBody ClientRequestDto clientRequestDto){
        return clientService.updateById(id, clientRequestDto);
    }
}
