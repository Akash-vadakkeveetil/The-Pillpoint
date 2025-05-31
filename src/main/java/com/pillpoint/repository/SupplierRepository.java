package com.pillpoint.repository;

import com.pillpoint.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findByUserId(Long userId);
    Optional<Supplier> findByLicenseNumber(String licenseNumber);
}
