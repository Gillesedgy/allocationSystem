package com.cas.clientAllocationSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto {
    private Long id;
    private Long clientId; // To link with Client
    private Long rateCardId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String billingSchedule;  // Enum as String

}
