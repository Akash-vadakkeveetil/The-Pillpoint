package com.pillpoint.repository;

import com.pillpoint.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByHospitalId(Long hospitalId);
    List<Order> findBySupplierId(Long supplierId);
    List<Order> findByStatus(Order.Status status);
    List<Order> findByUrgency(Order.Urgency urgency);
    List<Order> findByHospitalIdAndStatus(Long hospitalId, Order.Status status);
    List<Order> findBySupplierIdAndStatus(Long supplierId, Order.Status status);
}
