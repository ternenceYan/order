package com.imooc.order.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imooc.order.dto.OrderDto;
import com.imooc.order.form.OrderForm;
import com.imooc.order.pojo.OrderDetail;
import com.imooc.order.pojo.OrderTemp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderFormToOrderDtoTest {

    @Test
    public void convert() throws Exception {
        String str = "[{productid:\"000002\",productquantity:2},{productid:\"000001\",productquantity:2}]";
//        JSONObject object = JSON.parseObject(str);
        ArrayList<OrderTemp> list = JSON.parseObject(str, new TypeReference<ArrayList<OrderTemp>>() {
        });
        System.out.print("aa");
    }
}