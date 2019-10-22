package com.imooc.order.service;

import com.imooc.order.dto.OrderDto;
import com.imooc.order.pojo.OrderDetail;

public interface OrderService {
    /**
     * 创建订单(test)
     *
     * @return
     * @throws Exception
     */
    void createOrder() throws Exception;

    /**
     * 创建订单
     */
    OrderDto create(OrderDto orderDto) throws Exception;
}
