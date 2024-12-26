package com.gio.ordersystem.orders.service;

import com.gio.ordersystem.orders.domain.OrderDocument;
import com.gio.ordersystem.orders.repository.OrderDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderSearchService {

    private final OrderDocumentRepository orderDocumentRepository;

    @Autowired
    public OrderSearchService(OrderDocumentRepository orderDocumentRepository) {
        this.orderDocumentRepository = orderDocumentRepository;
    }

    public void saveOrder(OrderDocument orderDocument) {
        orderDocumentRepository.save(orderDocument);
    }

    public List<OrderDocument> searchOrdersByUserId(Long userId) {
        return orderDocumentRepository.findByUserId(userId);
    }

    public List<OrderDocument> searchOrdersByProductId(Long productId) {
        return orderDocumentRepository.findByProductId(productId);
    }
}
