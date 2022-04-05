package com.scut.comsumer;

import com.scut.service.HelloService;
import com.scut.zookeeper.ProxyFactory;

public class Comsumer {

    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(HelloService.class,"hadoop:2181","dubbo");

        String result = helloService.sayHello("lys");
        System.out.println(result);

        result = helloService.sayHello("lys");
        System.out.println(result);

    }
}
