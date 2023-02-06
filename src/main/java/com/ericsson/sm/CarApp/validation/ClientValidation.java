package com.ericsson.sm.CarApp.validation;

import com.ericsson.sm.CarApp.exception.GenericValidationException;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.model.CarService;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class ClientValidation {
    private final ClientRepository clientRepository;

    public void validate(Client client){
        if(client.getFirstName().isBlank()){
            throw new GenericValidationException("First name is empty");
        }
        if(client.getLastName().isBlank()){
            throw new GenericValidationException("Last name is empty");
        }
        if(client.getOib().isBlank()){
            throw new GenericValidationException("Oib is empty");
        }
        if(client.getOib().length() != 11){
            throw new GenericValidationException("Oib length is not correct");
        }
        if(!client.getOib().matches("^\\d+$")){
            throw new GenericValidationException("Oib format is not correct");
        }
        validateEmail(client.getEmail());
    }

    public void existsById(Long id){
        if(!clientRepository.existsById(id)){
            throw new EntityNotFoundException("Car with id " + id + " does not exist");
        }
    }

    public void checkIfClientOwnsCarAndCarHasCarService(Client client, Car car, CarService carService){
        if(client != null){
            if(!client.getCars().contains(car)){
                throw new EntityNotFoundException("Client does not own that car");
            }
        }

        if(car != null){
            if(!car.getCarServices().contains(carService)){
                throw new EntityNotFoundException("Car does not have that Car Service");
            }
        }
    }

    public void validateEmail(String email){
        if(!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            throw new GenericValidationException("Email format is not valid");
        }
    }
}
