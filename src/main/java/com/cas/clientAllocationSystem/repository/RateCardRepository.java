package com.cas.clientAllocationSystem.repository;

import com.cas.clientAllocationSystem.Enum.Role;
import com.cas.clientAllocationSystem.entity.RateCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RateCardRepository extends JpaRepository<RateCard, Long> {
    Optional<RateCard> findByRole(Role role);


}
