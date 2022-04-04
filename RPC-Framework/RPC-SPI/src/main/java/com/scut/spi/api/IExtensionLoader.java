package com.scut.spi.api;

public interface IExtensionLoader<T> {
    T getExtension(String alias);
}
