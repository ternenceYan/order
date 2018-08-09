package com.imooc.order.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.imooc.order.dto.OrderDto;
import com.imooc.order.form.OrderForm;
import com.imooc.order.pojo.OrderTemp;

import java.util.ArrayList;

public class OrderFormToOrderDto {

    public static OrderDto convert(OrderForm orderForm) {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyername(orderForm.getName());
        orderDto.setBuyeropenid(orderForm.getOpenid());
        orderDto.setBuyerphone(orderForm.getPhone());
        orderDto.setBuyeraddress(orderForm.getAddress());

//        String str = "[{productid:\"000002\",productquantity:2},{productid:\"000001\",productquantity:2}]";
//        ArrayList<OrderTemp> list = JSON.parseObject(str,new TypeReference<ArrayList<OrderTemp>>(){});
        ArrayList<OrderTemp> list = JSON.parseObject(orderForm.getItems(),new TypeReference<ArrayList<OrderTemp>>(){});
        orderDto.setOrderDetailList(list);
        return orderDto;
     }
}
