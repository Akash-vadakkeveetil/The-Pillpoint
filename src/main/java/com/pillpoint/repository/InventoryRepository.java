package com.pillpoint.repository;

import com.pillpoint.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByHospitalId(Long hospitalId);
    Optional<Inventory> findByHospitalIdAndMedicineId(Long hospitalId, Long medicineId);

    @Query("SELECT i FROM Inventory i WHERE i.currentQuantity < i.medicine.thresholdQuantity")
    List<Inventory> findLowStockInventories();

    @Query("SELECT i FROM Inventory i WHERE i.hospital.id = :hospitalId AND i.currentQuantity < i.medicine.thresholdQuantity")
    List<Inventory> findLowStockInventoriesByHospital(Long hospitalId);
}
