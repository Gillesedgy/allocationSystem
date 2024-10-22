package com.cas.clientAllocationSystem.repository;

import com.cas.clientAllocationSystem.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    // Find all contracts for a specific client
    List<Contract> findByClient_Id(Long clientId);

    // Find contracts within a specific date range
    List<Contract> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

    // Find active contracts based on the current date
    List<Contract> findByEndDateAfter(LocalDate currentDate);

    // Optional: Find contracts by rate card ID
    List<Contract> findByRateCard_Id(Long rateCardId);
}
