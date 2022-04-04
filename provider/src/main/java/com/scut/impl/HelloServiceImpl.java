package com.scut.impl;

import com.scut.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String userName) {
        return "Hello: " + userName;
    }
}
