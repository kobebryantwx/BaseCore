package com.kbryant.quickcore.execute.impl;

import com.kbryant.quickcore.event.RespEvent;
import com.kbryant.quickcore.event.ViewEvent;
import com.kbryant.quickcore.execute.IModelAndView;
import com.kbryant.quickcore.mvp.model.ModelHelper;
import com.kbryant.quickcore.util.ApiException;
import com.kbryant.quickcore.util.RespBase;

import io.reactivex.Flowable;

public class ModelAndView<V> implements IModelAndView<V> {
    private ModelHelper modelHelper;
    private V view;

    public static <V> ModelAndView<V> create(V view, ModelHelper modelHelper) {
        return new ModelAndView<V>(view, modelHelper);
    }

    ModelAndView(V view, ModelHelper modelHelper) {
        this.view = view;
        this.modelHelper = modelHelper;
    }

    @Override
    public <T> void request(Flowable<RespBase<T>> respResult,
                            final ViewEvent<V, T> okVet,
                            final ViewEvent<V, ApiException> badVet) {
        modelHelper.request(respResult, new RespEvent<T>() {
            @Override
            public void isOk(T t) {
                if (okVet != null) {
                    okVet.call(view, t);
                }
            }

            @Override
            public void isError(ApiException apiException) {
                if (badVet != null) {
                    badVet.call(view, apiException);
                }
            }
        });
    }

    @Override
    public <T> void request(Flowable<RespBase<T>> respResult, ViewEvent<V, T> okVet) {
        this.request(respResult, okVet, null);
    }
}
