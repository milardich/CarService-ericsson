package com.ericsson.sm.CarApp.repositories;


import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void testFindClientsByFirstNameOrLastName_returnsNotNull(){
        Page<Client> client = clientRepository.findByFirstOrLastName_sortedByLastNameASC("Stjepan", "", Pageable.unpaged());
        Assertions.assertNotNull(client);
    }
}
