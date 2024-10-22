package com.cas.clientAllocationSystem.service;

import com.cas.clientAllocationSystem.dto.ClientDto;
import com.cas.clientAllocationSystem.entity.Client;
import com.cas.clientAllocationSystem.mapper.ClientMapper;
import com.cas.clientAllocationSystem.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Create a new client.
     *
     * @param clientDto The DTO containing client data.
     * @return The saved ClientDto.
     */
    public ClientDto createClient(ClientDto clientDto) {
        Client client = ClientMapper.INSTANCE.toClientEntity(clientDto);
        Client savedClient = clientRepository.save(client);
        return ClientMapper.INSTANCE.toClientDto(savedClient);
    }

    /**
     * Retrieve all clients.
     *
     * @return A list of ClientDto.
     */
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(ClientMapper.INSTANCE::toClientDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve a specific client by ID.
     *
     * @param id The client ID.
     * @return The ClientDto.
     */
    public ClientDto getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        return ClientMapper.INSTANCE.toClientDto(client);
    }

    /**
     * Update a client's data.
     *
     * @param id        The client ID.
     * @param clientDto The new client data.
     * @return The updated ClientDto.
     */
    public ClientDto updateClient(Long id, ClientDto clientDto) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        existingClient.setName(clientDto.getName());
        existingClient.setAddress(clientDto.getAddress());
        existingClient.setContactDetails(clientDto.getContactDetails());

        Client updatedClient = clientRepository.save(existingClient);
        return ClientMapper.INSTANCE.toClientDto(updatedClient);
    }

    /**
     * Delete a client by ID.
     *
     * @param id The client ID.
     */
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new IllegalArgumentException("Client not found");
        }
        clientRepository.deleteById(id);
    }
}

