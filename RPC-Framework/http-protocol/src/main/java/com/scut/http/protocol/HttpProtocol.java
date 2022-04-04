package com.scut.http.protocol;


import com.scut.protocol.Protocol;
import com.scut.spi.annotation.api.Invocation;
import com.scut.spi.annotation.api.URL;

public class HttpProtocol implements Protocol {
    @Override
    public void start(URL url) {
        new HttpServer().start(url.getHostnName(),url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        return new HttpClient().send(url.getHostnName(),url.getPort(),invocation);
    }
}
