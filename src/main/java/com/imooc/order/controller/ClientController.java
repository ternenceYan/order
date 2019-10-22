package com.imooc.order.controller;

import com.imooc.order.client.FeignTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class ClientController {

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private FeignTest feignTest;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier(value = "restTemplate1")
    private RestTemplate restTemplate1;


    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        /**
         * 第一种方式：通过直接写入URL来调用服务
         */
        String response1 = restTemplate.getForObject("http://localhost:8081/msg",String.class);

        /**
         * 第二种方式：利用LoadBalanceClient通过应用名获取url，然后再使用RestTemplate
         */
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort()+"/msg");
        String response = restTemplate.getForObject(url,String.class);
        /**
         * 第三种方式：利用@LoadBalanced可在RestTemplate使用应用名字
         */
        String response3 = restTemplate.getForObject("http://PRODUCT/msg", String.class);


        /**
         * 含有参数的REST请求
         */
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);

        MultiValueMap<String,String> postParams = new LinkedMultiValueMap<>();
        postParams.add("id","1");
        postParams.add("name","tom");

        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<MultiValueMap<String,String>>(postParams,headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://PRODUCT/msg",entity,String.class);
        String jsonData = responseEntity.getBody();

        logger.info("response={}", response);
        return response;
    }

    //通过Feign来调用服务
    @RequestMapping("/getMsg")
    public String getMsg(String id,String name) {
        String response = feignTest.getMsg(id,name);
        logger.info("response={}", response);
        return response;
    }

    @RequestMapping("/msg")
    public String getProductMsg2 () {
        /**
         * 含有参数的REST请求
         */
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);

        MultiValueMap<String,String> postParams = new LinkedMultiValueMap<>();
        postParams.add("id","1");
        postParams.add("name","tom");

        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<MultiValueMap<String,String>>(postParams,headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8081/product/msg",null,String.class);
        String jsonData = responseEntity.getBody();
        return jsonData;
    }

    @RequestMapping("/msg3")
    public String getProductMsg3 () {
//        String response3 = restTemplate1.getForObject("http://localhost:8081/product/msg", String.class);
        String response3 = restTemplate1.getForObject("http://PRODUCT/msg", String.class);
        return response3;
    }

    @RequestMapping("/hello")
    public String msgPrint () {
        return "hello order";
    }
}
