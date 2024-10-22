package com.cas.clientAllocationSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.cas.clientAllocationSystem.entity")
@EnableJpaRepositories(basePackages = "com.cas.clientAllocationSystem.repository")
public class ClientAllocationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientAllocationSystemApplication.class, args);
    }

}
