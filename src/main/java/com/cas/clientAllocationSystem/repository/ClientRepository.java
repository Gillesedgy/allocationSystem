package com.cas.clientAllocationSystem.repository;

import com.cas.clientAllocationSystem.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
//    List<Client> findAll();
//
//    Optional<Client> findByName(String name);
}
