package com.pillpoint.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @NotNull
    @Column(name = "requested_quantity")
    private Integer requestedQuantity;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Urgency urgency;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status;

    @Column(name = "estimated_value")
    private Double estimatedValue;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @Column(name = "fulfillment_date")
    private LocalDateTime fulfillmentDate;

    public enum Urgency {
        LOW, MEDIUM, HIGH, CRITICAL
    }

    public enum Status {
        NEW, REVIEWING, ACCEPTED, DECLINED, FULFILLED
    }

    // Constructors
    public Order() {}

    public Order(Hospital hospital, Medicine medicine, Integer requestedQuantity, Urgency urgency) {
        this.hospital = hospital;
        this.medicine = medicine;
        this.requestedQuantity = requestedQuantity;
        this.urgency = urgency;
        this.status = Status.NEW;
        this.requestDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Hospital getHospital() { return hospital; }
    public void setHospital(Hospital hospital) { this.hospital = hospital; }

    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }

    public Medicine getMedicine() { return medicine; }
    public void setMedicine(Medicine medicine) { this.medicine = medicine; }

    public Integer getRequestedQuantity() { return requestedQuantity; }
    public void setRequestedQuantity(Integer requestedQuantity) { this.requestedQuantity = requestedQuantity; }

    public Urgency getUrgency() { return urgency; }
    public void setUrgency(Urgency urgency) { this.urgency = urgency; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Double getEstimatedValue() { return estimatedValue; }
    public void setEstimatedValue(Double estimatedValue) { this.estimatedValue = estimatedValue; }

    public LocalDateTime getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDateTime requestDate) { this.requestDate = requestDate; }

    public LocalDateTime getFulfillmentDate() { return fulfillmentDate; }
    public void setFulfillmentDate(LocalDateTime fulfillmentDate) { this.fulfillmentDate = fulfillmentDate; }
}
