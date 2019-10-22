package com.imooc.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT")
public interface FeignTest {

    @RequestMapping("/product/msg1")
    // 此处的方法名随便定义
    public String getMsg(@RequestParam("id") String id,@RequestParam("name") String name);
}
