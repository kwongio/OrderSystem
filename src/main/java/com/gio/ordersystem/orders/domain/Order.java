package com.gio.ordersystem.orders.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("주문 고유 ID")
    private Long id;

    @Column(nullable = false, columnDefinition = "BIGINT NOT NULL")
    @Comment("사용자 ID")
    private Long userId;

    @Column(nullable = false, columnDefinition = "BIGINT NOT NULL")
    @Comment("상품 ID")
    private Long productId;

    @Column(nullable = false, columnDefinition = "INT NOT NULL CHECK (quantity > 0)")
    @Comment("주문 수량 (0 이상)")
    private Integer quantity;

    @Column(nullable = false, precision = 10, scale = 2, columnDefinition = "DECIMAL(10, 2) NOT NULL CHECK (total_price >= 0)")
    @Comment("총 주문 금액 (0 이상)")
    private BigDecimal totalPrice;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Comment("주문 날짜")
    @CreatedDate
    private LocalDateTime orderDate;

    @Builder
    public Order(Long id, Long userId, Long productId, Integer quantity, BigDecimal totalPrice, LocalDateTime orderDate) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }
}
