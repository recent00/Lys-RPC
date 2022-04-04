package com.scut.dubbo.protocol;

import com.scut.spi.annotation.api.Invocation;
import com.scut.spi.annotation.api.URL;
import com.scut.protocol.Protocol;

public class DubboProtocol implements Protocol {
    @Override
    public void start(URL url) {
        new NettyServer().start(url.getHostnName(),url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        return new NettyClient().send(url.getHostnName(),url.getPort(),invocation);
    }
}
