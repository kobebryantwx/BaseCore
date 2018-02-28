package com.kbryant.quickcore.event;

import com.kbryant.quickcore.util.ApiException;

public interface RespEvent<T> {
    void isOk(T t);

    void isError(ApiException apiException);
}
