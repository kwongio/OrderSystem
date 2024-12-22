package com.gio.ordersystem.orders.controller;

import com.gio.ordersystem.orders.dto.OrderCreateRequest;
import com.gio.ordersystem.orders.dto.OrderCreateResponse;
import com.gio.ordersystem.orders.dto.OrderQueryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "주문 시스템 API", description = "주문 생성, 조회 및 삭제를 제공하는 API")
public interface OrderSwaggerController {

    @Operation(summary = "주문 생성", description = "새로운 주문을 생성합니다.")
    @ApiResponse(responseCode = "201", description = "주문 생성 성공")
    ResponseEntity<OrderCreateResponse> createOrder(OrderCreateRequest request);

    @Operation(summary = "주문 조회", description = "특정 주문의 상세 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "주문 조회 성공")
    ResponseEntity<OrderQueryResponse> getOrder(@PathVariable Long orderId);

    @Operation(summary = "전체 주문 조회", description = "모든 주문 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "전체 주문 조회 성공")
    ResponseEntity<List<OrderQueryResponse>> getAllOrders();

    @Operation(summary = "주문 삭제", description = "특정 주문을 삭제합니다.")
    @ApiResponse(responseCode = "204", description = "주문 삭제 성공")
    ResponseEntity<Void> deleteOrder(@PathVariable Long orderId);
}
