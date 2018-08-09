package com.imooc.order.service.impl;

import com.imooc.order.dto.OrderDto;
import com.imooc.order.enums.OrderStatusEnum;
import com.imooc.order.mapper.OrderDetailMapper;
import com.imooc.order.mapper.OrderMasterMapper;
import com.imooc.order.pojo.OrderDetail;
import com.imooc.order.pojo.OrderMaster;
import com.imooc.order.service.OrderService;
import com.imooc.order.utils.KeyUtikl;
import org.apache.ibatis.annotations.One;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    /**
     * 订单test
     * @throws Exception
     */
    @Override
    public void createOrder() throws Exception {

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCreatetime(new Date());
        orderDetail.setDetailid("002");
        orderDetail.setOrderid("01");
        orderDetail.setProductid("000002");
        orderDetail.setProductname("西瓜");
        orderDetail.setProductprice(BigDecimal.ONE);
        orderDetail.setProductquantity(2);
        orderDetail.setUpdatetime(new Date());

        orderDetailMapper.insert(orderDetail);
    }

    /**
     *   1.查询商品信息（调用商品服务）
     *   2.计算总价
     *   3.扣库存（调用商品服务）
     *   4.订单入库
     *
     * @param orderDto
     * @return
     * @throws Exception
     */
    @Override
    public OrderDto create(OrderDto orderDto) throws Exception {

        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderid(orderDto.getOrderid());
        orderMaster.setBuyername(orderDto.getBuyername());
        orderMaster.setBuyeraddress(orderDto.getBuyeraddress());
        orderMaster.setBuyeropenid(orderDto.getBuyeropenid());
        orderMaster.setBuyerphone(orderDto.getBuyerphone());
        orderMaster.setBuyeropenid(orderDto.getBuyeropenid());
        //遍历商品，计算总价
//        BigDecimal amount = null;
//        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
//            BigDecimal price = orderDetail.getProductprice();
//            amount = amount.add(price.multiply(new BigDecimal(orderDetail.getProductquantity())));
//        }
        orderMaster.setOrderamount(BigDecimal.ONE);
        orderMaster.setOrderstatus(OrderStatusEnum.WAIT.getCode());
        orderMaster.setPaystatus(1);
        orderMaster.setOrderid(KeyUtikl.getUniqueKey());
        orderDto.setOrderid(KeyUtikl.getUniqueKey());
        orderMasterMapper.insert(orderMaster);

        return orderDto;
    }
}
