package com.kbryant.quickcore.util;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

public final class TargetSubscribeEvent<T> extends ResourceSubscriber<T> {
    private EventCall<? super T> okEvent;
    private EventCall<? super ApiException> badEvent;

    TargetSubscribeEvent(EventCall<? super T> okEvent) {
        this(okEvent, null);
    }

    public TargetSubscribeEvent(EventCall<? super T> okEvent, EventCall<? super ApiException> badEvent) {
        this.okEvent = okEvent;
        this.badEvent = badEvent;
    }

    @Override
    public void onNext(T t) {
        if (okEvent != null) {
            okEvent.call(t);
        }
    }

    @Override
    public void onError(Throwable t) {
        if (badEvent != null && t instanceof ApiException) {
            ApiException apiException = (ApiException) t;
            badEvent.call(apiException);
        } else if (t instanceof HttpException) {
            HttpException httpException = (HttpException) t;
            ApiException apiException = new ApiException("网络请求失败", httpException.code());
            badEvent.call(apiException);
        } else {
            ApiException apiException = new ApiException(t.getLocalizedMessage());
            badEvent.call(apiException);
        }
    }

    @Override
    public void onComplete() {

    }
}
