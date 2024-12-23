package com.gio.ordersystem.orders.controller;

import com.gio.ordersystem.orders.dto.OrderCreateRequest;
import com.gio.ordersystem.orders.dto.OrderCreateResponse;
import com.gio.ordersystem.orders.dto.OrderQueryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "주문 시스템 API", description = "주문 생성, 조회 및 삭제를 제공하는 API")
public interface OrderSwaggerController {

    @Operation(
            summary = "주문 생성",
            description = "새로운 주문을 생성합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "주문 생성 요청 데이터",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderCreateRequest.class),
                            examples = @ExampleObject(
                                    name = "주문 생성 요청 예제",
                                    value = """
                                            {
                                                "userId": 1,
                                                "productId": 101,
                                                "quantity": 3,
                                                "totalPrice": 90000
                                            }
                                            """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "주문 생성 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = OrderCreateResponse.class),
                                    examples = @ExampleObject(
                                            name = "주문 생성 응답 예제",
                                            value = """
                                                    {
                                                        "orderId": 12345,
                                                        "status": "CREATED"
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청")
            }
    )
    ResponseEntity<OrderCreateResponse> createOrder(OrderCreateRequest request);

    @Operation(
            summary = "주문 상세 조회",
            description = "특정 주문 ID에 대한 상세 정보를 반환합니다.",
            parameters = @Parameter(
                    name = "orderId",
                    description = "조회할 주문의 ID",
                    required = true,
                    example = "12345"
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "주문 조회 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = OrderQueryResponse.class),
                                    examples = @ExampleObject(
                                            name = "주문 조회 응답 예제",
                                            value = """
                                                    {
                                                        "orderId": 12345,
                                                        "userId": 1,
                                                        "productId": 101,
                                                        "quantity": 3,
                                                        "totalPrice": 90000,
                                                        "orderDate": "2024-12-23T15:30:00"
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "주문을 찾을 수 없음")
            }
    )
    ResponseEntity<OrderQueryResponse> getOrder(@PathVariable Long orderId);

    @GetMapping
    @Operation(
            summary = "주문 목록 조회 (No-Offset 방식)",
            description = """
                    `No-Offset` 기반의 페이징을 사용하여 주문 목록을 조회합니다.
                    - `lastId`를 기준으로 이후 데이터를 가져옵니다.
                    - 요청 페이지 크기는 `pageSize`로 지정할 수 있습니다.
                    """,
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "주문 목록 조회 성공"
            )
    )
    public ResponseEntity<List<OrderQueryResponse>> getOrders(
            @RequestParam(defaultValue = "1") @Parameter(name = "lastId", description = "마지막으로 조회된 주문 ID (기본값: 1)") long lastId,
            @RequestParam(defaultValue = "20") @Parameter(name = "pageSize", description = "가져올 주문 개수 (기본값: 20)") int pageSize
    );

    @Operation(
            summary = "주문 삭제",
            description = "특정 주문 ID에 해당하는 주문을 삭제합니다.",
            parameters = @Parameter(
                    name = "orderId",
                    description = "삭제할 주문의 ID",
                    required = true,
                    example = "12345"
            ),
            responses = {
                    @ApiResponse(responseCode = "204", description = "주문 삭제 성공"),
                    @ApiResponse(responseCode = "404", description = "주문을 찾을 수 없음")
            }
    )
    ResponseEntity<Void> deleteOrder(@PathVariable Long orderId);
}
