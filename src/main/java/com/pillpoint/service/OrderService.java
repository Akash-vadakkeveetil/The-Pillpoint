package com.pillpoint.service;

import com.pillpoint.model.Hospital;
import com.pillpoint.model.Medicine;
import com.pillpoint.model.Order;
import com.pillpoint.model.Supplier;
import com.pillpoint.repository.HospitalRepository;
import com.pillpoint.repository.MedicineRepository;
import com.pillpoint.repository.OrderRepository;
import com.pillpoint.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public List<Order> getOrdersByHospitalId(Long hospitalId) {
        return orderRepository.findByHospitalId(hospitalId);
    }

    public List<Order> getOrdersBySupplierId(Long supplierId) {
        return orderRepository.findBySupplierId(supplierId);
    }

    public List<Order> getOrdersByStatus(Order.Status status) {
        return orderRepository.findByStatus(status);
    }

    public List<Order> getOrdersByUrgency(Order.Urgency urgency) {
        return orderRepository.findByUrgency(urgency);
    }

    public List<Order> getOrdersByHospitalAndStatus(Long hospitalId, Order.Status status) {
        return orderRepository.findByHospitalIdAndStatus(hospitalId, status);
    }

    public List<Order> getOrdersBySupplierAndStatus(Long supplierId, Order.Status status) {
        return orderRepository.findBySupplierIdAndStatus(supplierId, status);
    }

    @Transactional
    public Order createOrder(Long hospitalId, Long medicineId, Integer quantity, Order.Urgency urgency) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new RuntimeException("Hospital not found with id: " + hospitalId));

        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + medicineId));

        Order order = new Order(hospital, medicine, quantity, urgency);
        return orderRepository.save(order);
    }

    @Transactional
    public Order assignOrderToSupplier(Long orderId, Long supplierId, Double estimatedValue) {
        Order order = getOrderById(orderId);
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + supplierId));

        order.setSupplier(supplier);
        order.setEstimatedValue(estimatedValue);
        order.setStatus(Order.Status.ACCEPTED);

        return orderRepository.save(order);
    }

    @Transactional
    public Order updateOrderStatus(Long orderId, Order.Status status) {
        Order order = getOrderById(orderId);
        order.setStatus(status);

        if (status == Order.Status.FULFILLED) {
            order.setFulfillmentDate(LocalDateTime.now());
        }

        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
