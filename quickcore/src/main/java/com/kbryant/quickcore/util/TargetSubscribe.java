package com.kbryant.quickcore.util;

import io.reactivex.subscribers.ResourceSubscriber;
import com.kbryant.quickcore.event.RespEvent;

public class TargetSubscribe<T> extends ResourceSubscriber<T> {
    private RespEvent<T> respEvent;

    public TargetSubscribe(RespEvent<T> respEvent) {
        this.respEvent = respEvent;
    }

    @Override
    public void onNext(T t) {
        if (respEvent != null) {
            respEvent.isOk(t);
        }
    }

    @Override
    public void onError(Throwable t) {
        if (respEvent != null && t instanceof ApiException) {
            respEvent.isError((ApiException) t);
        }
    }

    @Override
    public void onComplete() {

    }
}
