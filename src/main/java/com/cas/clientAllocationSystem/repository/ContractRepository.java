package com.cas.clientAllocationSystem.repository;

import com.cas.clientAllocationSystem.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findByEndDateAfter(LocalDate date);
}
