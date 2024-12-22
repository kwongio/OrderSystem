package com.gio.ordersystem.orders.controller;

import com.gio.ordersystem.orders.domain.Order;
import com.gio.ordersystem.orders.dto.OrderCreateRequest;
import com.gio.ordersystem.orders.dto.OrderCreateResponse;
import com.gio.ordersystem.orders.dto.OrderQueryResponse;
import com.gio.ordersystem.orders.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController implements OrderSwaggerController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderCreateResponse> createOrder(@Valid @RequestBody OrderCreateRequest request) {
        Order order = orderService.createOrder(request.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(OrderCreateResponse.from(order));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderQueryResponse> getOrder(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId).orElseThrow(() -> new RuntimeException("Order not found."));
        return ResponseEntity.ok(OrderQueryResponse.from(order));
    }

    @GetMapping
    public ResponseEntity<List<OrderQueryResponse>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders.stream()
                .map(OrderQueryResponse::from)
                .toList());
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
