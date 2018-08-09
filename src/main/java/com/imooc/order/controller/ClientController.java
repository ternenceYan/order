package com.imooc.order.controller;

import com.imooc.order.client.FeignTest;
import com.imooc.order.config.RestTemplateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class ClientController {

    Logger logger = LoggerFactory.getLogger(OrderController.class);

//    @Autowired
//    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private FeignTest feignTest;
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        //第一种方式：通过直接写入URL来调用服务
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:8081/msg",String.class);

        //第二种方式：利用LoadBalanceClient通过应用名获取url，然后再使用RestTemplate
//        RestTemplate restTemplate = new RestTemplate();
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        String url = String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort()+"/msg");
//        String response = restTemplate.getForObject(url,String.class);
        //第三种方式：利用@LoadBalanced可在RestTemplate使用应用名字
        String response = restTemplate.getForObject("http://PRODUCT/msg",String.class);
        logger.info("response={}",response);
        return response;
    }

    //通过Feign来调用服务
    @GetMapping("/getMsg")
    public String getMsg() {
        String response = feignTest.getMsg();
        logger.info("response={}",response);
        return response;
    }
}
