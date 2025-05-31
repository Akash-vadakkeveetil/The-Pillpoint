package com.pillpoint.service;

import com.pillpoint.model.Medicine;
import com.pillpoint.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;

    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    public Medicine getMedicineById(Long id) {
        return medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + id));
    }

    public Medicine getMedicineByName(String name) {
        return medicineRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Medicine not found with name: " + name));
    }

    public List<Medicine> getMedicinesByCategory(String category) {
        return medicineRepository.findByCategory(category);
    }

    public Medicine createMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    public Medicine updateMedicineThreshold(Long medicineId, Integer newThreshold) {
        Medicine medicine = getMedicineById(medicineId);
        medicine.setThresholdQuantity(newThreshold);
        return medicineRepository.save(medicine);
    }

    public void deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
    }
}
