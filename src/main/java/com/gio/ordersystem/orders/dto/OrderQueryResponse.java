package com.gio.ordersystem.orders.dto;

import com.gio.ordersystem.orders.domain.Order;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderQueryResponse {

    private long orderId;
    private long userId;
    private long productId;
    private int quantity;
    private long totalPrice;
    private LocalDateTime orderDate;

    public static OrderQueryResponse from(Order order) {
        OrderQueryResponse response = new OrderQueryResponse();
        response.setOrderId(order.getId());
        response.setUserId(order.getUserId());
        response.setProductId(order.getProductId());
        response.setQuantity(order.getQuantity());
        response.setTotalPrice(order.getTotalPrice());
        response.setOrderDate(order.getOrderDate());
        return response;
    }
}
