package com.scut.comsumer;

import com.scut.protocol.Protocol;
import com.scut.service.HelloService;
import com.scut.spi.api.impl.ExtensionLoader;
import com.scut.spi.bs.SpiBs;
import com.scut.zookeeper.ProxyFactory;

public class Comsumer {

    public static void main(String[] args) {

        ExtensionLoader<Protocol> load = SpiBs.load(Protocol.class);
        Protocol protocol = load.getExtension("dubbo");
        HelloService helloService = ProxyFactory.getProxy(HelloService.class,"hadoop:2181",protocol);

        String result = helloService.sayHello("lys");
        System.out.println(result);

        result = helloService.sayHello("lys");
        System.out.println(result);

    }
}
