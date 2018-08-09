package com.imooc.order.client;

import com.imooc.order.pojo.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "PRODUCT")
public interface ProductClient {
    @PostMapping("/product/listForOrder")
    List<ProductInfo> getProductInfo(List<String> productIdList);
}
