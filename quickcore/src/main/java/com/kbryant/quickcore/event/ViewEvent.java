package com.kbryant.quickcore.event;

public interface ViewEvent<V, T> {
    void call(V view, T data);
}
