package com.scut.http.protocol;

import com.alibaba.fastjson.JSONObject;
import com.scut.local.register.LocalRegister;
import com.scut.spi.annotation.api.Invocation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.commons.io.IOUtils;

/**
 * 处理servlet请求
 */
public class HttpServerHandler {
    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        //处理请求的逻辑
        //反序列化，取出数据
        try {
            Invocation invocation = JSONObject.parseObject(req.getInputStream(), Invocation.class);

            //获取实现类
            Class implClass = LocalRegister.get(invocation.getInterfaceName());
            Method method = null;
            try {
                method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
                String result = (String) method.invoke(implClass.newInstance(), invocation.getParams());

                IOUtils.write(result,resp.getOutputStream());

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
