package com.gio.ordersystem.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ExceptionCode {
    ORDER_NOT_FOUND("ORDER-001", HttpStatus.NOT_FOUND, "주문을 찾을 수 없습니다."),
    ;


    private final String code;
    private final HttpStatus status;
    private final String message;

    ExceptionCode(String code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
