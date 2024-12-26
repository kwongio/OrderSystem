package com.gio.ordersystem.orders.service;

import com.gio.ordersystem.exception.CustomException;
import com.gio.ordersystem.exception.ExceptionCode;
import com.gio.ordersystem.orders.domain.Order;
import com.gio.ordersystem.orders.domain.OrderDocument;
import com.gio.ordersystem.orders.repository.OrderDocumentRepository;
import com.gio.ordersystem.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDocumentRepository orderDocumentRepository;

    @Transactional
    @CachePut(value = "orders", key = "#result.id")
    public Order create(Order order) {
        Order saveOrder = orderRepository.save(order);
        OrderDocument orderDocument = mapToDocument(saveOrder);
        orderDocumentRepository.save(orderDocument);
        return saveOrder;
    }
    private OrderDocument mapToDocument(Order order) {
        return OrderDocument.builder()
                .id(order.getId().toString())
                .userId(order.getUserId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .orderDate(order.getOrderDate())
                .build();
    }
    @Transactional(readOnly = true)
    @Cacheable(value = "orders", key = "#id") // 조회 시 캐시 사용
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new CustomException(ExceptionCode.ORDER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<Order> getOrdersAfter(Long lastId, int pageSize) {
        // 전체 데이터는 캐싱하지 않음 (특정 페이지에 대해 필요 시 캐싱 가능)
        return orderRepository.findNextOrders(lastId, pageSize);
    }

    @Transactional
    @CacheEvict(value = "orders", key = "#id") // 삭제 시 해당 캐시 데이터 제거
    public void deleteById(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new CustomException(ExceptionCode.ORDER_NOT_FOUND);
        }
        orderRepository.deleteById(id);
    }


}
