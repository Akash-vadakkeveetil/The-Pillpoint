package com.pillpoint.security;

import com.pillpoint.model.Hospital;
import com.pillpoint.model.Supplier;
import com.pillpoint.repository.HospitalRepository;
import com.pillpoint.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public boolean isCurrentUser(Authentication authentication, Long userId) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getId().equals(userId);
    }

    public boolean isHospitalUser(Authentication authentication, Long hospitalId) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return hospitalRepository.findByUserId(userPrincipal.getId())
                .map(hospital -> hospital.getId().equals(hospitalId))
                .orElse(false);
    }

    public boolean isSupplierUser(Authentication authentication, Long supplierId) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return supplierRepository.findByUserId(userPrincipal.getId())
                .map(supplier -> supplier.getId().equals(supplierId))
                .orElse(false);
    }
}
