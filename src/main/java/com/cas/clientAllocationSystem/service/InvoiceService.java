//package com.cas.clientAllocationSystem.service;
//
//import com.cas.clientAllocationSystem.Enum.BillingPeriod;
//import com.cas.clientAllocationSystem.entity.Client;
//import com.cas.clientAllocationSystem.entity.Invoice;
//import com.cas.clientAllocationSystem.entity.TimeLog;
//import com.cas.clientAllocationSystem.repository.ClientRepository;
//import com.cas.clientAllocationSystem.repository.InvoiceRepository;
//import com.cas.clientAllocationSystem.repository.TimeLogRepository;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Set;
//
//@Service
//public class InvoiceService {
//
//    private InvoiceRepository invoiceRepository;
//
//    private ClientRepository clientRepository;
//
//    private TimeLogRepository timeLogRepository;
//
//    public InvoiceService(InvoiceRepository invoiceRepository, ClientRepository clientRepository, TimeLogRepository timeLogRepository) {
//        this.invoiceRepository = invoiceRepository;
//        this.clientRepository = clientRepository;
//        this.timeLogRepository = timeLogRepository;
//    }
//
//    // 1. Generate invoice for a client based on billing period
////    @Transactional
////    public Invoice generateInvoice(Long clientId, LocalDate startDate, LocalDate endDate) {
////        Client client = clientRepository.findById(clientId)
////                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
////
////        // Get all time logs for the client's projects within the billing period
////        List<TimeLog> timeLogs = timeLogRepository.findByProjectAssignment_Project_ClientIdAndDateBetween(
////                clientId, startDate, endDate
////        );
////
////        // Calculate total amount based on role rates
////        BigDecimal totalAmount = calculateTotalAmount(timeLogs);
////
////        // Create and save the invoice
////        Invoice invoice = new Invoice(null, client, totalAmount, LocalDate.now(), BillingPeriod.MONTHLY, Set.of());
////        invoiceRepository.save(invoice);
////
////        return invoice;
////    }
//
//    // 2. Calculate total amount from time logs
//    private BigDecimal calculateTotalAmount(List<TimeLog> timeLogs) {
//        return timeLogs.stream()
//                .map(log -> log.getAssignment().getStaff().getRole().getHourlyRate()
//                        .multiply(BigDecimal.valueOf(log.getHoursWorked())))
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//    }
//
//    // 3. Retrieve all invoices for a client
//    public List<Invoice> getInvoicesForClient(Long clientId) {
//        return invoiceRepository.findByClientId(clientId);
//    }
//}
//
