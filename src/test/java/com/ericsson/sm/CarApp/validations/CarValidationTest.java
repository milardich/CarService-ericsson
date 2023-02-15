package com.ericsson.sm.CarApp.validations;


import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.exception.GenericValidationException;
import com.ericsson.sm.CarApp.repository.CarRepository;
import com.ericsson.sm.CarApp.validation.CarValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CarValidationTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarValidation carValidation;

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
}
