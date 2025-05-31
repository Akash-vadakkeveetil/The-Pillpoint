package com.pillpoint.controller;

import com.pillpoint.model.Order;
import com.pillpoint.model.Supplier;
import com.pillpoint.service.OrderService;
import com.pillpoint.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('HOSPITAL')")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPPLIER') and @securityService.isSupplierUser(authentication, #id)")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPPLIER') and @securityService.isCurrentUser(authentication, #userId)")
    public ResponseEntity<Supplier> getSupplierByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(supplierService.getSupplierByUserId(userId));
    }

    @GetMapping("/{supplierId}/orders")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPPLIER') and @securityService.isSupplierUser(authentication, #supplierId)")
    public ResponseEntity<List<Order>> getSupplierOrders(@PathVariable Long supplierId) {
        return ResponseEntity.ok(orderService.getOrdersBySupplierId(supplierId));
    }

    @GetMapping("/{supplierId}/orders/{status}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPPLIER') and @securityService.isSupplierUser(authentication, #supplierId)")
    public ResponseEntity<List<Order>> getSupplierOrdersByStatus(
            @PathVariable Long supplierId,
            @PathVariable String status) {

        Order.Status orderStatus = Order.Status.valueOf(status);
        return ResponseEntity.ok(orderService.getOrdersBySupplierAndStatus(supplierId, orderStatus));
    }

    @PutMapping("/orders/{orderId}/assign")
    @PreAuthorize("hasRole('SUPPLIER')")
    public ResponseEntity<Order> assignOrderToSupplier(
            @PathVariable Long orderId,
            @RequestBody Map<String, Object> assignRequest) {

        Long supplierId = Long.valueOf(assignRequest.get("supplierId").toString());
        Double estimatedValue = Double.valueOf(assignRequest.get("estimatedValue").toString());

        Order updatedOrder = orderService.assignOrderToSupplier(orderId, supplierId, estimatedValue);
        return ResponseEntity.ok(updatedOrder);
    }

    @PutMapping("/orders/{orderId}/status")
    @PreAuthorize("hasRole('SUPPLIER')")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam String status) {

        Order.Status orderStatus = Order.Status.valueOf(status);
        Order updatedOrder = orderService.updateOrderStatus(orderId, orderStatus);
        return ResponseEntity.ok(updatedOrder);
    }

    @PutMapping("/{supplierId}/rating")
    @PreAuthorize("hasRole('ADMIN') or hasRole('HOSPITAL')")
    public ResponseEntity<Supplier> updateSupplierRating(
            @PathVariable Long supplierId,
            @RequestParam Double rating) {

        Supplier updatedSupplier = supplierService.updateSupplierRating(supplierId, rating);
        return ResponseEntity.ok(updatedSupplier);
    }
}
