package com.gio.ordersystem.orders.repository;

import com.gio.ordersystem.orders.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
