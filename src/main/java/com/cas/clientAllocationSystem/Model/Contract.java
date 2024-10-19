package com.cas.clientAllocationSystem.Model;

import com.cas.clientAllocationSystem.Enum.BillingSchedule;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contracts",schema = "allocationSystemV2")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING) // Changed to Enum
    @Column(nullable = false)
    private BillingSchedule billingSchedule;

    @ManyToOne
    @JoinColumn(name = "rate_card_id", nullable = false)
    private RateCard rateCard;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Project> projects = new HashSet<>();


}

