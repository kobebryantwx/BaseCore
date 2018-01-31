package com.kbryant.quickcore.util;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RXUtil {
    /**
     * 统一线程切换处理
     * @param <T> 对象类型
     * @return Flowable转换器
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper(){
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(@NonNull Flowable<T> upstream) {
                return upstream
                        //下游事件在主线程中处理
                        .observeOn(AndroidSchedulers.mainThread())
                        //上游事件在IO线程中处理
                        .subscribeOn(Schedulers.io());
            }
        };
    }

    /**
     * 统一结果处理
     * @param <T> 对象类型
     * @return Flowable转换器
     */
    public static <T> FlowableTransformer<RespBase<T>,T> handleRespBaseResult(){
        return new FlowableTransformer<RespBase<T>,T>(){
            @Override
            public Publisher<T> apply(@NonNull Flowable<RespBase<T>> upstream) {
                return upstream.flatMap(new Function<RespBase<T>, Publisher<T>>() {
                    @Override
                    public Publisher<T> apply(@NonNull RespBase<T> tRespBase) throws Exception {
                        if(tRespBase.getCode() == 1){
                            return createData(tRespBase.getData());
                        }else{
                            return Flowable.error(new ApiException(tRespBase.getMsg(),tRespBase.getCode(),tRespBase.getIs_alert()==1));
                        }
                    }
                });
            }
        };
    }

    /**
     * 新建流对象
     * @param t 传输对象
     * @param <T> 对象类型
     * @return 流对象
     */
    private static <T> Flowable<T> createData(final T t){
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<T> e) throws Exception {
                e.onNext(t);
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);
    }
}
