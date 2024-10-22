package com.cas.clientAllocationSystem.repository;

import com.cas.clientAllocationSystem.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByContractId(Long contractId);
}
