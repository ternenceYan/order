package com.imooc.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "PRODUCT")
public interface FeignTest {

    @GetMapping("/product/msg")
    public String getMsg();
}
