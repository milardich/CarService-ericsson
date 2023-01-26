package com.ericsson.sm.CarApp.repository;

import com.ericsson.sm.CarApp.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c WHERE (?1 is null OR c.firstName = ?1) AND (?2 is null OR c.lastName = ?2) ORDER BY c.lastName ASC")
    Page<Client> findAllByFirstNameOrLastName(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            Pageable pageable
    );
}
