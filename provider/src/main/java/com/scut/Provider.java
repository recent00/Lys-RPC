package com.scut;

import com.scut.spi.annotation.api.URL;
import com.scut.impl.HelloServiceImpl;
import com.scut.local.register.LocalRegister;
import com.scut.protocol.Protocol;
import com.scut.service.HelloService;
import com.scut.spi.api.impl.ExtensionLoader;
import com.scut.spi.bs.SpiBs;
import com.scut.zookeeper.Register;

public class Provider {

    public static void main(String[] args) {
        //本地注册
        LocalRegister.regist(HelloService.class.getName(), HelloServiceImpl.class);

        //注册中心注册
        URL url = new URL("localhost",8082);
        Register.register(HelloService.class.getName(),url);
        //启动tomcat/netty
        ExtensionLoader<Protocol> load = SpiBs.load(Protocol.class);
        Protocol protocol = load.getExtension("dubbo");
        System.out.println(protocol);
        protocol.start(url);
    }
}
