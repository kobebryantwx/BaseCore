package com.kbryant.quickcore.event;

import com.kbryant.quickcore.util.ApiException;

/**
 * 返回结果回调接口
 *
 * @param <T>
 */
public interface RespEvent<T> {
    void isOk(T t);

    void isError(ApiException apiException);
}
