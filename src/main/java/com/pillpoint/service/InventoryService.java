package com.pillpoint.service;

import com.pillpoint.model.Inventory;
import com.pillpoint.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + id));
    }

    public Inventory getInventoryByHospitalAndMedicine(Long hospitalId, Long medicineId) {
        return inventoryRepository.findByHospitalIdAndMedicineId(hospitalId, medicineId)
                .orElseThrow(() -> new RuntimeException("Inventory not found for hospital id: " + hospitalId + " and medicine id: " + medicineId));
    }

    public List<Inventory> getLowStockInventories() {
        return inventoryRepository.findLowStockInventories();
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}
