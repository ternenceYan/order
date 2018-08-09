package com.imooc.order.controller;

import com.imooc.order.VO.ResultVO;
import com.imooc.order.VO.ResultVOUtil;
import com.imooc.order.client.ProductClient;
import com.imooc.order.converter.OrderFormToOrderDto;
import com.imooc.order.dto.OrderDto;
import com.imooc.order.exception.OrderException;
import com.imooc.order.form.OrderForm;
import com.imooc.order.pojo.OrderDetail;
import com.imooc.order.pojo.OrderTemp;
import com.imooc.order.pojo.ProductInfo;
import com.imooc.order.service.OrderService;
import com.sun.xml.internal.bind.v2.model.util.ArrayInfoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProductClient productClient;

    @RequestMapping("createOrderDetail")
    public String createOrder() throws  Exception{
        orderService.createOrder();
        return "ok";
    }
    /**
     * 1.参数校验
     * 2.查询商品信息（调用商品服务）
     * 3.计算总价
     * 4.扣库存（调用商品服务）
     * 5.订单入库
     */
    @RequestMapping("/create")
    public ResultVO<Map<String,String>> create (@Valid OrderForm orderForm, BindingResult bindingResult) throws Exception{
        if (bindingResult.hasErrors()) {
            List<ObjectError> ls = bindingResult.getAllErrors();
            for (int i = 0; i < ls.size(); i++) {
                logger.error("error:"+ls.get(i));
            }
//            logger.error("【创建订单】参数不正确，orderForm={}",orderForm);
        }
        //orderForm->orderDto
        OrderDto orderDto = OrderFormToOrderDto.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            logger.error("购物车为空");
        }
       //查询商品信息
        List<String> productIdList = new ArrayList<>();
        for (OrderTemp orderDetail : orderDto.getOrderDetailList()) {
            productIdList.add(orderDetail.getProductid());
        }
        logger.info("开始准备调用商品服务，查询商品列表=======================");
        List<ProductInfo> productInfoList = productClient.getProductInfo(productIdList);
        OrderDto result = orderService.create(orderDto);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderid());
        return ResultVOUtil.success(map);
    }


    @GetMapping("/getProductList")
    public  List<ProductInfo> getProductList() throws Exception{
        List<String> productIdList = new ArrayList<>();
        productIdList.add("000001");
        productIdList.add("000002");
        logger.info("========================开始准备调用商品服务，获取商品列表 ========================");
        List<ProductInfo> productInfoList = productClient.getProductInfo(productIdList);
        return productInfoList;
    }
}
