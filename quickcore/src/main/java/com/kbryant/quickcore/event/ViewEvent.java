package com.kbryant.quickcore.event;

/**
 * 请求结果返回接口
 *
 * @param <V>
 * @param <T>
 */
public interface ViewEvent<V, T> {
    void call(V view, T data);
}
