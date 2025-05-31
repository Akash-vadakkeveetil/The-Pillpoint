package com.pillpoint.service;

import com.pillpoint.model.Hospital;
import com.pillpoint.model.Inventory;
import com.pillpoint.model.Medicine;
import com.pillpoint.repository.HospitalRepository;
import com.pillpoint.repository.InventoryRepository;
import com.pillpoint.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    public Hospital getHospitalById(Long id) {
        return hospitalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hospital not found with id: " + id));
    }

    public Hospital getHospitalByUserId(Long userId) {
        return hospitalRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Hospital not found for user id: " + userId));
    }

    public List<Hospital> getHospitalsWithLowCompliance(Integer threshold) {
        return hospitalRepository.findHospitalsWithLowCompliance(threshold);
    }

    @Transactional
    public void updateHospitalComplianceScore(Long hospitalId, Integer newScore) {
        Hospital hospital = getHospitalById(hospitalId);
        hospital.setComplianceScore(newScore);
        hospitalRepository.save(hospital);
    }

    @Transactional
    public Inventory updateInventory(Long hospitalId, Long medicineId, Integer quantity, LocalDateTime expiryDate) {
        Hospital hospital = getHospitalById(hospitalId);
        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + medicineId));

        Optional<Inventory> existingInventory = inventoryRepository.findByHospitalIdAndMedicineId(hospitalId, medicineId);

        if (existingInventory.isPresent()) {
            Inventory inventory = existingInventory.get();
            inventory.setCurrentQuantity(quantity);
            if (expiryDate != null) {
                inventory.setExpiryDate(expiryDate);
            }
            return inventoryRepository.save(inventory);
        } else {
            Inventory newInventory = new Inventory(hospital, medicine, quantity, expiryDate);
            return inventoryRepository.save(newInventory);
        }
    }

    public List<Inventory> getHospitalInventory(Long hospitalId) {
        return inventoryRepository.findByHospitalId(hospitalId);
    }

    public List<Inventory> getLowStockInventoriesByHospital(Long hospitalId) {
        return inventoryRepository.findLowStockInventoriesByHospital(hospitalId);
    }
}
