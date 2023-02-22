package com.ericsson.sm.CarApp.validations;


import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.exception.GenericValidationException;
import com.ericsson.sm.CarApp.repository.CarRepository;
import com.ericsson.sm.CarApp.repository.CarServiceRepository;
import com.ericsson.sm.CarApp.validation.CarValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CarValidationTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarValidation carValidation;
    @Autowired
    private CarServiceRepository carServiceRepository;

    @Test
    void testEmptyColor_throwsGenericValidationException(){
        CarRequestDto carRequestDto = new CarRequestDto();
        carRequestDto.setManufactureYear(1999);
        carRequestDto.setCarType("OPEL_ASTRA");
        carRequestDto.setColor("");
        carRequestDto.setRegistrationMark("WW 222 WW");

        Throwable exception = Assertions.assertThrows(GenericValidationException.class, () -> carValidation.validate(carRequestDto));

        Assertions.assertEquals(GenericValidationException.class, exception.getClass());
    }

    @Test
    void testIncorrectRegistrationMarkFormat_throwsGenericValidationException(){
        CarRequestDto carRequestDto = new CarRequestDto();
        carRequestDto.setManufactureYear(1999);
        carRequestDto.setCarType("OPEL_ASTRA");
        carRequestDto.setColor("Red");
        carRequestDto.setRegistrationMark("WW 222554 WW");

        Throwable exception = Assertions.assertThrows(GenericValidationException.class, () -> carValidation.validate(carRequestDto));

        Assertions.assertEquals(GenericValidationException.class, exception.getClass());
    }

    @Test
    void testInvalidCarType_throwsGenericValidationException(){
        CarRequestDto carRequestDto = new CarRequestDto();
        carRequestDto.setManufactureYear(2999);
        carRequestDto.setCarType("MUSTANG");
        carRequestDto.setColor("Red");
        carRequestDto.setRegistrationMark("WW 22554 WW");

        Throwable exception = Assertions.assertThrows(GenericValidationException.class, () -> carValidation.validate(carRequestDto));

        Assertions.assertEquals(GenericValidationException.class, exception.getClass());
    }
}
