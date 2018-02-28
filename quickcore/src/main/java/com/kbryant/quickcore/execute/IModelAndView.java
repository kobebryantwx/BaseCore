package com.kbryant.quickcore.execute;

import com.kbryant.quickcore.event.ViewEvent;
import com.kbryant.quickcore.util.ApiException;

import io.reactivex.Flowable;

import com.kbryant.quickcore.util.RespBase;

public interface IModelAndView<V> {
    <T> void request(Flowable<RespBase<T>> respResult, ViewEvent<V, T> okVet, ViewEvent<V, ApiException> badVet);

    <T> void request(Flowable<RespBase<T>> respResult, ViewEvent<V, T> okVet);
}
