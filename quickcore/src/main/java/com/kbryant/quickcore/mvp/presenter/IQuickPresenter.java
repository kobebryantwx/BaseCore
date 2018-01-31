package com.kbryant.quickcore.mvp.presenter;

import com.kbryant.quickcore.mvp.IPresenter;
import com.kbryant.quickcore.mvp.model.ModelHelper;

public interface IQuickPresenter extends IPresenter {
    /**
     * 提供Service
     * @param serviceClass 指定Service类
     * @param <T> Service类型
     * @return Service对象
     */
    <T> T service(Class<T> serviceClass);
    /**
     * 提供Model助手类
     * @return Model助手对象
     */
    ModelHelper modelHelper();
}
