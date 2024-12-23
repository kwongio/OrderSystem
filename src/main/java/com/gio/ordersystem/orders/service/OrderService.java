package com.gio.ordersystem.orders.service;

import com.gio.ordersystem.exception.CustomException;
import com.gio.ordersystem.exception.ExceptionCode;
import com.gio.ordersystem.orders.domain.Order;
import com.gio.ordersystem.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order create(Order order) {
        return orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new CustomException(ExceptionCode.ORDER_NOT_FOUND));
    }

    public List<Order> getOrdersAfter(Long lastId, int pageSize) {
        return orderRepository.findNextOrders(lastId, pageSize);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
