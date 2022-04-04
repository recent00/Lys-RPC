package com.scut.protocol;


import com.scut.spi.annotation.SPI;
import com.scut.spi.annotation.api.Invocation;
import com.scut.spi.annotation.api.URL;

@SPI
public interface Protocol {
    void start(URL url);

    String send(URL url, Invocation invocation);
}
