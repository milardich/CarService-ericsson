package com.ericsson.sm.CarApp.validation;

import com.ericsson.sm.CarApp.repository.CarServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class CarServiceValidation {
    private final CarServiceRepository carServiceRepository;

    public void existsById(Long id){
        if(!carServiceRepository.existsById(id)){
            throw new EntityNotFoundException("Car Service with id " + id + " not found");
        }
    }
}
