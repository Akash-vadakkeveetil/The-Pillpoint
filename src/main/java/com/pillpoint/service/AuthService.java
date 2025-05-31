package com.pillpoint.service;

import com.pillpoint.dto.JwtResponse;
import com.pillpoint.dto.LoginRequest;
import com.pillpoint.dto.SignupRequest;
import com.pillpoint.model.Hospital;
import com.pillpoint.model.Supplier;
import com.pillpoint.model.User;
import com.pillpoint.repository.HospitalRepository;
import com.pillpoint.repository.SupplierRepository;
import com.pillpoint.repository.UserRepository;
import com.pillpoint.security.JwtUtils;
import com.pillpoint.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return new JwtResponse(
                jwt,
                userPrincipal.getId(),
                userPrincipal.getUsername(),
                userPrincipal.getEmail(),
                userRepository.findById(userPrincipal.getId())
                        .orElseThrow(() -> new RuntimeException("User not found"))
                        .getRole()
        );
    }

    @Transactional
    public void registerUser(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("Email is already in use");
        }

        // Create user account
        User user = new User(
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()),
                signupRequest.getRole()
        );

        User savedUser = userRepository.save(user);

        // Create additional profile based on role
        if (signupRequest.getRole() == User.Role.HOSPITAL) {
            Hospital hospital = new Hospital(
                    user,
                    signupRequest.getOrganizationName(),
                    signupRequest.getLocation(),
                    signupRequest.getLicenseNumber()
            );
            hospitalRepository.save(hospital);
        } else if (signupRequest.getRole() == User.Role.SUPPLIER) {
            Supplier supplier = new Supplier(
                    user,
                    signupRequest.getOrganizationName(),
                    signupRequest.getLocation(),
                    signupRequest.getLicenseNumber()
            );
            supplierRepository.save(supplier);
        }
    }
}
