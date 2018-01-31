package com.kbryant.quickcore.mvp.model;

import io.reactivex.Flowable;
import com.kbryant.quickcore.event.RespEvent;
import com.kbryant.quickcore.util.ApiException;
import com.kbryant.quickcore.util.EventCall;
import com.kbryant.quickcore.util.RespBase;

public interface IModelHelper {
    /**
     * 获取指定Service实例
     *
     * @param serviceClass Service类
     * @param <T>          Service类型
     * @return service实例对象
     */
    <T> T getService(Class<T> serviceClass);

    /**
     * 包装Flowable对象
     *
     * @param respResult 未包装的对象
     * @param <T>        回调数据类型
     * @return 包装后的对象
     */
    <T> Flowable<T> pack(Flowable<RespBase<T>> respResult);

    /**
     * 请求事件（统一接口回调）
     *
     * @param respResult 经过Retrofit包装返回的Flowable对象
     * @param event      事件回调
     * @param <T>        回调数据类型
     */
    <T> void request(Flowable<RespBase<T>> respResult, RespEvent<T> event);

    /**
     * 请求事件（成功+失败事件回调）
     *
     * @param respResult 经过Retrofit包装返回的Flowable对象
     * @param okEvent    成功事件回调
     * @param badEvent   失败事件回调
     * @param <T>        回调数据类型
     */
    <T> void request(Flowable<RespBase<T>> respResult, EventCall<? super T> okEvent, EventCall<? super ApiException> badEvent);

    /**
     * 请求事件（不带失败事件回调）
     *
     * @param respResult 经过Retrofit包装返回的Flowable对象
     * @param okEvent    成功事件回调
     * @param <T>        回调数据类型
     */
    <T> void request(Flowable<RespBase<T>> respResult, EventCall<? super T> okEvent);

    /**
     * 解除事件流绑定
     */
    void unBind();
}
