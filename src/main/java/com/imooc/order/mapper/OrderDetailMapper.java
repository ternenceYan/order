package com.imooc.order.mapper;

import com.imooc.order.pojo.OrderDetail;
import com.imooc.order.utils.MyMapper;


public interface OrderDetailMapper extends MyMapper<OrderDetail> {

    /**
     * 创建订单
     *
     * @return
     * @throws Exception
     */
    OrderDetail createOrder(OrderDetail orderDetail) throws Exception;

    int insert(OrderDetail record);
}