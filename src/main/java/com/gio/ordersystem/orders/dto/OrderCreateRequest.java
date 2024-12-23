package com.gio.ordersystem.orders.dto;

import com.gio.ordersystem.orders.domain.Order;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderCreateRequest {

    @NotNull(message = "사용자 ID는 필수입니다.")
    private long userId;

    @NotNull(message = "상품 ID는 필수입니다.")
    private long productId;

    @Min(value = 1, message = "주문 수량은 1 이상이어야 합니다.")
    private int quantity;

    @NotNull(message = "총 주문 금액은 필수입니다.")
    private long totalPrice;

    public Order toEntity() {
        return Order.builder()
                .userId(userId)
                .productId(productId)
                .quantity(quantity)
                .totalPrice(totalPrice)
                .build();
    }
}
