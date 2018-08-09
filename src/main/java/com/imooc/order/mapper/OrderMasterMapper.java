package com.imooc.order.mapper;

import com.imooc.order.pojo.OrderMaster;
import com.imooc.order.utils.MyMapper;

import java.util.List;

public interface OrderMasterMapper extends MyMapper<OrderMaster> {

    @Override
    int insert(OrderMaster orderMaster);
}