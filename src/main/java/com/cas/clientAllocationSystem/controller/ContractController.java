package com.cas.clientAllocationSystem.controller;

import com.cas.clientAllocationSystem.dto.ContractDto;
import com.cas.clientAllocationSystem.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping
    public ContractDto createContract(@RequestBody ContractDto dto) {
        return contractService.createContract(dto);
    }

    @PutMapping("/{id}")
    public ContractDto updateContract(@PathVariable Long id, @RequestBody ContractDto dto) {
        return contractService.updateContract(id, dto);
    }

    @GetMapping
    public List<ContractDto> getAllContracts() {
        return contractService.getAllContracts();
    }

    @GetMapping("/client/{clientId}")
    public List<ContractDto> getContractsByClientId(@PathVariable Long clientId) {
        return contractService.getContractsByClientId(clientId);
    }
}
