package com.kbryant.quickcore.mvp.model;

import com.kbryant.quickcore.util.ApiException;
import com.kbryant.quickcore.util.RXUtil;
import com.kbryant.quickcore.util.TargetSubscribe;
import com.kbryant.quickcore.util.TargetSubscribeEvent;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import com.kbryant.quickcore.event.RespEvent;
import com.kbryant.quickcore.repository.impl.RepositoryStore;
import com.kbryant.quickcore.util.EventCall;
import com.kbryant.quickcore.util.RespBase;

public class ModelHelper implements IModelHelper {
    private CompositeDisposable compositeDisposable;
    private RepositoryStore repositoryStore;

    @Inject
    public ModelHelper(RepositoryStore repositoryStore) {
        compositeDisposable = new CompositeDisposable();
        this.repositoryStore = repositoryStore;
    }

    @Override
    public <T> T getService(Class<T> serviceClass) {
        return repositoryStore.getRetrofitService(serviceClass);
    }

    @Override
    public <T> Flowable<T> pack(Flowable<RespBase<T>> respResult) {
        return respResult
                //统一处理线程切换
                .compose(RXUtil.<RespBase<T>>rxSchedulerHelper())
                //统一处理返回对象
                .compose(RXUtil.<T>handleRespBaseResult());
    }

    @Override
    public <T> void request(Flowable<RespBase<T>> respResult, RespEvent<T> event) {
        //将RXJava流对象绑定的observer对象加入compositeDisposable统一管理
        compositeDisposable.add(pack(respResult)
                //自定义事件回调
                .subscribeWith(new TargetSubscribe<T>(event)));
    }

    @Override
    public <T> void request(Flowable<RespBase<T>> respResult, EventCall<? super T> okEvent, EventCall<? super ApiException> badEvent) {
        //将RXJava流对象绑定的observer对象加入compositeDisposable统一管理
        compositeDisposable.add(pack(respResult)
                //自定义事件回调
                .subscribeWith(new TargetSubscribeEvent<T>(okEvent, badEvent)));
    }

    @Override
    public <T> void request(Flowable<RespBase<T>> respResult, EventCall<? super T> okEvent) {
        this.request(respResult, okEvent, null);
    }


    @Override
    public void unBind() {
        compositeDisposable.clear();
    }
}
