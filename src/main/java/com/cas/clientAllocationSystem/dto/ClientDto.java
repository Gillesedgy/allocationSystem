package com.cas.clientAllocationSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private Long id;

    @NotBlank(message = "Client name is mandatory")
    @Size(max = 100, message = "Client name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Contact details are mandatory")
    private String contactDetails;
}

