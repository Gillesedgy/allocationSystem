package com.cas.clientAllocationSystem.repository;

import com.cas.clientAllocationSystem.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAll();
    Optional<Client> findByName(String name);
}
