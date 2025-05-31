package com.pillpoint.controller;

import com.pillpoint.model.Hospital;
import com.pillpoint.model.Inventory;
import com.pillpoint.model.Order;
import com.pillpoint.service.HospitalService;
import com.pillpoint.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Hospital>> getAllHospitals() {
        return ResponseEntity.ok(hospitalService.getAllHospitals());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('HOSPITAL') and @securityService.isHospitalUser(authentication, #id)")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable Long id) {
        return ResponseEntity.ok(hospitalService.getHospitalById(id));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('HOSPITAL') and @securityService.isCurrentUser(authentication, #userId)")
    public ResponseEntity<Hospital> getHospitalByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(hospitalService.getHospitalByUserId(userId));
    }

    @GetMapping("/{hospitalId}/inventory")
    @PreAuthorize("hasRole('ADMIN') or hasRole('HOSPITAL') and @securityService.isHospitalUser(authentication, #hospitalId)")
    public ResponseEntity<List<Inventory>> getHospitalInventory(@PathVariable Long hospitalId) {
        return ResponseEntity.ok(hospitalService.getHospitalInventory(hospitalId));
    }

    @GetMapping("/{hospitalId}/low-stock")
    @PreAuthorize("hasRole('ADMIN') or hasRole('HOSPITAL') and @securityService.isHospitalUser(authentication, #hospitalId)")
    public ResponseEntity<List<Inventory>> getLowStockInventories(@PathVariable Long hospitalId) {
        return ResponseEntity.ok(hospitalService.getLowStockInventoriesByHospital(hospitalId));
    }

    @PutMapping("/{hospitalId}/inventory")
    @PreAuthorize("hasRole('HOSPITAL') and @securityService.isHospitalUser(authentication, #hospitalId)")
    public ResponseEntity<Inventory> updateInventory(
            @PathVariable Long hospitalId,
            @RequestParam Long medicineId,
            @RequestParam Integer quantity,
            @RequestParam(required = false) LocalDateTime expiryDate) {

        Inventory updatedInventory = hospitalService.updateInventory(hospitalId, medicineId, quantity, expiryDate);
        return ResponseEntity.ok(updatedInventory);
    }

    @GetMapping("/{hospitalId}/orders")
    @PreAuthorize("hasRole('ADMIN') or hasRole('HOSPITAL') and @securityService.isHospitalUser(authentication, #hospitalId)")
    public ResponseEntity<List<Order>> getHospitalOrders(@PathVariable Long hospitalId) {
        return ResponseEntity.ok(orderService.getOrdersByHospitalId(hospitalId));
    }

    @PostMapping("/{hospitalId}/orders")
    @PreAuthorize("hasRole('HOSPITAL') and @securityService.isHospitalUser(authentication, #hospitalId)")
    public ResponseEntity<Order> createOrder(
            @PathVariable Long hospitalId,
            @RequestBody Map<String, Object> orderRequest) {

        Long medicineId = Long.valueOf(orderRequest.get("medicineId").toString());
        Integer quantity = Integer.valueOf(orderRequest.get("quantity").toString());
        String urgencyStr = orderRequest.get("urgency").toString();
        Order.Urgency urgency = Order.Urgency.valueOf(urgencyStr);

        Order newOrder = orderService.createOrder(hospitalId, medicineId, quantity, urgency);
        return ResponseEntity.ok(newOrder);
    }

    @GetMapping("/{hospitalId}/orders/{status}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('HOSPITAL') and @securityService.isHospitalUser(authentication, #hospitalId)")
    public ResponseEntity<List<Order>> getHospitalOrdersByStatus(
            @PathVariable Long hospitalId,
            @PathVariable String status) {

        Order.Status orderStatus = Order.Status.valueOf(status);
        return ResponseEntity.ok(orderService.getOrdersByHospitalAndStatus(hospitalId, orderStatus));
    }

    @PutMapping("/{hospitalId}/compliance")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateComplianceScore(
            @PathVariable Long hospitalId,
            @RequestParam Integer score) {

        hospitalService.updateHospitalComplianceScore(hospitalId, score);
        return ResponseEntity.ok("Compliance score updated successfully");
    }

    @GetMapping("/low-compliance")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Hospital>> getHospitalsWithLowCompliance(@RequestParam Integer threshold) {
        return ResponseEntity.ok(hospitalService.getHospitalsWithLowCompliance(threshold));
    }
}
