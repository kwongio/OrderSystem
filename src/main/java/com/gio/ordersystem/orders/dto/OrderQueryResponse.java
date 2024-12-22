package com.gio.ordersystem.orders.dto;

import com.gio.ordersystem.orders.domain.Order;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderQueryResponse {

    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private BigDecimal totalPrice;
    private LocalDateTime orderDate;

    public static OrderQueryResponse from(Order order) {
        OrderQueryResponse response = new OrderQueryResponse();
        response.setId(order.getId());
        response.setUserId(order.getUserId());
        response.setProductId(order.getProductId());
        response.setQuantity(order.getQuantity());
        response.setTotalPrice(order.getTotalPrice());
        response.setOrderDate(order.getOrderDate());
        return response;
    }
}
