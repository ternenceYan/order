package com.imooc.order.utils;

import java.util.Random;

public class KeyUtikl {
    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     */
    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer number  = random.nextInt(90000)+10000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
