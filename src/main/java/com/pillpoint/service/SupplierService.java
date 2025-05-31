package com.pillpoint.service;

import com.pillpoint.model.Supplier;
import com.pillpoint.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
    }

    public Supplier getSupplierByUserId(Long userId) {
        return supplierRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Supplier not found for user id: " + userId));
    }

    public Supplier updateSupplierRating(Long supplierId, Double newRating) {
        Supplier supplier = getSupplierById(supplierId);
        supplier.setRating(newRating);
        return supplierRepository.save(supplier);
    }
}
