package com.ericsson.sm.CarApp.validation;

import com.ericsson.sm.CarApp.exception.GenericValidationException;
import com.ericsson.sm.CarApp.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientValidation {
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
    }
}
