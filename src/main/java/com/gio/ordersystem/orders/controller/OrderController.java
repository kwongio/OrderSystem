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
        Order order = orderService.create(request.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(OrderCreateResponse.from(order));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderQueryResponse> getOrder(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok(OrderQueryResponse.from(orderService.findById(orderId)));
    }

    @GetMapping
    public ResponseEntity<List<OrderQueryResponse>> getOrders(
            @RequestParam(defaultValue = "1", name = "lastId") long lastId,
            @RequestParam(defaultValue = "20",  name = "pageSize") int pageSize
    ) {
        List<Order> orders = orderService.getOrdersAfter(lastId, pageSize);
        List<OrderQueryResponse> response = orders.stream()
                .map(OrderQueryResponse::from)
                .toList();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") Long orderId) {
        orderService.deleteById(orderId);
        return ResponseEntity.noContent().build();
    }
}
