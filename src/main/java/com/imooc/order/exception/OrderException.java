package com.imooc.order.exception;

public class OrderException {

    private Integer code;

    private String message;

    public OrderException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
