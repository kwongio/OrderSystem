package com.gio.ordersystem.orders.repository;

import com.gio.ordersystem.orders.domain.OrderDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface OrderDocumentRepository extends ElasticsearchRepository<OrderDocument, String> {
    List<OrderDocument> findByUserId(Long userId);
    List<OrderDocument> findByProductId(Long productId);
}
