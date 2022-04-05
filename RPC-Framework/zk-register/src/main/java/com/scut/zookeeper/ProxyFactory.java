package com.scut.zookeeper;

import com.scut.spi.annotation.api.Invocation;
import com.scut.spi.annotation.api.LoadBalance;
import com.scut.spi.annotation.api.URL;
import com.scut.protocol.Protocol;
import com.scut.spi.api.impl.ExtensionLoader;
import com.scut.spi.bs.SpiBs;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProxyFactory {
    public static Map<String,List<URL>> map = new HashMap<>();//本地服务列表

    @SuppressWarnings("unchecked")
    public static <T> T getProxy(final Class interfaceClass,String address,Protocol protocol) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                try {

                    Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);

                    //List<URL> urls = RemoteMapRegister.get(interfaceClass.getName());
                    List<URL> urls;
                    if(map.size() == 0 || map.get(interfaceClass.getName()).size() == 0) {
                        Discovery discovery = new Discovery(address);
                        urls = discovery.discover(interfaceClass.getName());
                        map.put(interfaceClass.getName(),urls);
                    }

                    URL url = LoadBalance.random(map.get(interfaceClass.getName()));
                    System.out.println("------" + map.get(interfaceClass.getName()).size());
                    String result = protocol.send(url, invocation);
                    return result + url.getPort();
                }catch (Exception e) {
                    e.printStackTrace();
                    return "不好意思，执行出错了";
                }
            }
        });
    }
}
