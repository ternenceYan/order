package com.imooc.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cunsumer")
//使用默认的降级策略（熔断策略）
@DefaultProperties(defaultFallback = "defaultFallBack")
public class ConsumertHystrix {

    /**
     * 这里的fallback方法，是最下面定义的调用
     * @return
     */
    @RequestMapping("/test")
    @ResponseBody
    public String hystrixTest () {
        return "";
    }

    /**
     * 用于熔断的方法，这里进行了友好提示
     * @return
     */
    public String fallback () {
        return "product error";
    }

    /**
     * 定义熔断策略
     * @return
     */
    public String defaultFallBack () {
        return "默认的熔断策略，product error";
    }
}
