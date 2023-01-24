package com.ericsson.sm.CarApp.repository;

import com.ericsson.sm.CarApp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
