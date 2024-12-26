package com.gio.ordersystem.orders.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Document(indexName = "orders")
public class OrderDocument {

    @Id
    private String id; // Elasticsearch는 기본적으로 String ID 사용
    private Long userId;
    private Long productId;
    private int quantity;
    private long totalPrice;
    private LocalDateTime orderDate;

    @Builder
    public OrderDocument(String id, Long userId, Long productId, int quantity, long totalPrice, LocalDateTime orderDate) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }
}
