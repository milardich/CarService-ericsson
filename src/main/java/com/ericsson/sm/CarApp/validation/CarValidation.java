package com.ericsson.sm.CarApp.validation;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.exception.GenericValidationException;
import com.ericsson.sm.CarApp.model.enumeration.CarType;
import com.ericsson.sm.CarApp.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class CarValidation {

    private final CarRepository carRepository;

    public void validate(CarRequestDto carRequestDto){

        if(carRequestDto.getCarType() == null){
            throw new GenericValidationException("Car type is null");
        }
        if(!StringUtils.hasLength(carRequestDto.getCarType())){
            throw new GenericValidationException("Car type is empty");
        }
        if(!carTypeExists(carRequestDto.getCarType())) {
            throw new GenericValidationException("Car type does not exist");
        }

        if(carRequestDto.getManufactureYear() == null){
            throw new GenericValidationException("Manufacture year is null");
        }
        if(carRequestDto.getRegistrationMark().isBlank()){
            throw new GenericValidationException("Registration mark is blank");
        }

        checkRegistrationMarkFormat(carRequestDto.getRegistrationMark());

        if(!StringUtils.hasLength(carRequestDto.getColor())){
            throw new GenericValidationException("Color is blank");
        }
    }

    private void checkRegistrationMarkFormat(String registrationMark){

        if(registrationMark.length() < 9 || registrationMark.length() > 11){
            throw new GenericValidationException("Registration mark character count is out of bounds");
        }
        if(!Character.isLetter(registrationMark.charAt(0)) || !Character.isLetter(registrationMark.charAt(1))){
            throw new GenericValidationException("First two characters in registration mark are not letters");
        }
        if(!Character.isWhitespace(registrationMark.charAt(2))){
            throw new GenericValidationException("Third character in registration mark is not a space character");
        }
        if(!Character.isLetter(registrationMark.charAt(registrationMark.length() - 1)) ||
                !Character.isLetter(registrationMark.charAt(registrationMark.length() - 2))){
            throw new GenericValidationException("Last two characters in registration mark are not letters");
        }
        if(!Character.isWhitespace(registrationMark.charAt(registrationMark.length() - 3))){
            throw new GenericValidationException("Character after numbers in registration mark is not a space character");
        }

        int numberCount = registrationMark.length() - 6;

        if(numberCount < 3 || numberCount > 5){
            throw new GenericValidationException("Number count in registration mark is not between 3 and 5");
        }

        for(int i = 3; i < registrationMark.length() - 3; i++){
            if(!Character.isDigit(registrationMark.charAt(i))){
                throw new GenericValidationException("Non digit found in numbers section");
            }
        }
    }

    public void existsById(Long id){
        if(!carRepository.existsById(id)){
            throw new EntityNotFoundException("Car with id " + id + " does not exist");
        }
    }

    private boolean carTypeExists(String carType){
        for (CarType c : CarType.values()) {
            if (c.name().equals(carType)) {
                return true;
            }
        }
        return false;
    }
}
