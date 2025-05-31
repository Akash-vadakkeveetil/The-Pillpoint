package com.pillpoint.controller;

import com.pillpoint.model.Hospital;
import com.pillpoint.model.Inventory;
import com.pillpoint.model.Order;
import com.pillpoint.service.HospitalService;
import com.pillpoint.service.InventoryService;
import com.pillpoint.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getAdminDashboardData() {
        Map<String, Object> dashboardData = new HashMap<>();

        List<Hospital> hospitals = hospitalService.getAllHospitals();
        List<Hospital> lowComplianceHospitals = hospitalService.getHospitalsWithLowCompliance(70);
        List<Inventory> lowStockInventories = inventoryService.getLowStockInventories();

        List<Order> criticalOrders = orderService.getOrdersByUrgency(Order.Urgency.CRITICAL);
        List<Order> newOrders = orderService.getOrdersByStatus(Order.Status.NEW);
        List<Order> fulfilledOrders = orderService.getOrdersByStatus(Order.Status.FULFILLED);

        dashboardData.put("totalHospitals", hospitals.size());
        dashboardData.put("lowComplianceHospitals", lowComplianceHospitals.size());
        dashboardData.put("criticalAlerts", lowStockInventories.size());
        dashboardData.put("criticalOrders", criticalOrders.size());
        dashboardData.put("newOrders", newOrders.size());
        dashboardData.put("fulfilledOrders", fulfilledOrders.size());

        return ResponseEntity.ok(dashboardData);
    }

    @GetMapping("/hospital/dashboard")
    @PreAuthorize("hasRole('HOSPITAL')")
    public ResponseEntity<Map<String, Object>> getHospitalDashboardData() {
        // This would typically use the authenticated user's hospital ID
        // For demonstration, we'd implement the security service to extract hospital ID
        // from the authenticated user

        Map<String, Object> dashboardData = new HashMap<>();
        // dashboardData would include:
        // - Low stock inventories
        // - Orders by status
        // - Compliance metrics

        return ResponseEntity.ok(dashboardData);
    }

    @GetMapping("/supplier/dashboard")
    @PreAuthorize("hasRole('SUPPLIER')")
    public ResponseEntity<Map<String, Object>> getSupplierDashboardData() {
        // This would typically use the authenticated user's supplier ID
        // For demonstration, we'd implement the security service to extract supplier ID
        // from the authenticated user

        Map<String, Object> dashboardData = new HashMap<>();
        // dashboardData would include:
        // - Orders by status
        // - Order fulfillment metrics
        // - Hospital customer information

        return ResponseEntity.ok(dashboardData);
    }
}
