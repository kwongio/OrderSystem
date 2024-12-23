package com.gio.ordersystem.orders.repository;

import com.gio.ordersystem.orders.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders o WHERE o.id > :lastId ORDER BY o.id LIMIT :pageSize", nativeQuery = true)
    List<Order> findNextOrders(@Param("lastId") Long lastId, @Param("pageSize") int pageSize);
}
