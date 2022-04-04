package com.scut.dubbo.protocol;

import com.scut.protocol.Protocol;

public class ProtocolFactory {
    public static Protocol getProtocol() {
        return new DubboProtocol();
    }
}
