package com.pillpoint.repository;

import com.pillpoint.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    Optional<Hospital> findByUserId(Long userId);
    Optional<Hospital> findByLicenseNumber(String licenseNumber);

    @Query("SELECT h FROM Hospital h WHERE h.complianceScore < :threshold")
    List<Hospital> findHospitalsWithLowCompliance(Integer threshold);
}
