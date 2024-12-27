package com.gio.ordersystem.orders.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Document(indexName = "orders")
public class OrderDocument {

    @Id
    private String id; // Elasticsearch는 기본적으로 String ID 사용

    @Field(type = FieldType.Long)
    private Long userId;

    @Field(type = FieldType.Long)
    private Long productId;

    @Field(type = FieldType.Integer)
    private int quantity;

    @Field(type = FieldType.Long)
    private long totalPrice;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
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
