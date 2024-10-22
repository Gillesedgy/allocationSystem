package com.cas.clientAllocationSystem.service;

import com.cas.clientAllocationSystem.Enum.BillingSchedule;
import com.cas.clientAllocationSystem.dto.ContractDto;
import com.cas.clientAllocationSystem.entity.Client;
import com.cas.clientAllocationSystem.entity.Contract;
import com.cas.clientAllocationSystem.entity.RateCard;
import com.cas.clientAllocationSystem.mapper.ContractMapper;
import com.cas.clientAllocationSystem.repository.ClientRepository;
import com.cas.clientAllocationSystem.repository.ContractRepository;
import com.cas.clientAllocationSystem.repository.RateCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractService {

    private final ContractRepository contractRepository;
    private final ClientRepository clientRepository;
    private final RateCardRepository rateCardRepository;

    @Autowired
    public ContractService(ContractRepository contractRepository,
                           ClientRepository clientRepository,
                           RateCardRepository rateCardRepository) {
        this.contractRepository = contractRepository;
        this.clientRepository = clientRepository;
        this.rateCardRepository = rateCardRepository;
    }

    public List<ContractDto> getAllContracts() {
        return contractRepository.findAll().stream()
                .map(ContractMapper.INSTANCE::toContractDto)
                .collect(Collectors.toList());
    }

    public List<ContractDto> getContractsByClientId(Long clientId) {
        return contractRepository.findByClient_Id(clientId).stream()
                .map(ContractMapper.INSTANCE::toContractDto)
                .collect(Collectors.toList());
    }

    /**
     * Creates a new contract with proper validation.
     *
     * @param dto Contract data transfer object.
     * @return The saved ContractDto.
     */
    public ContractDto createContract(ContractDto dto) {
        validateContractDates(dto.getStartDate(), dto.getEndDate());

        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        RateCard rateCard = rateCardRepository.findById(dto.getRateCardId())
                .orElseThrow(() -> new IllegalArgumentException("RateCard not found"));

        Contract contract = ContractMapper.INSTANCE.toContractEntity(dto);
        contract.setClient(client);
        contract.setRateCard(rateCard);

        Contract savedContract = contractRepository.save(contract);
        return ContractMapper.INSTANCE.toContractDto(savedContract);
    }

    /**
     * Updates an existing contract.
     *
     * @param id  Contract ID.
     * @param dto Updated contract data.
     * @return The updated ContractDto.
     */
    public ContractDto updateContract(Long id, ContractDto dto) {
        validateContractDates(dto.getStartDate(), dto.getEndDate());

        Contract existingContract = contractRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contract not found"));

        existingContract.setStartDate(dto.getStartDate());
        existingContract.setEndDate(dto.getEndDate());
        existingContract.setBillingSchedule(BillingSchedule.valueOf(dto.getBillingSchedule()));

        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        existingContract.setClient(client);

        RateCard rateCard = rateCardRepository.findById(dto.getRateCardId())
                .orElseThrow(() -> new IllegalArgumentException("RateCard not found"));
        existingContract.setRateCard(rateCard);

        Contract updatedContract = contractRepository.save(existingContract);
        return ContractMapper.INSTANCE.toContractDto(updatedContract);
    }

    /**
     * Validates that the contract's start date is before the end date.
     *
     * @param startDate The start date.
     * @param endDate   The end date.
     */
    private void validateContractDates(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before end date.");
        }
    }


}
