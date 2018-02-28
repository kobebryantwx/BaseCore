package com.kbryant.quickcore.mvp.presenter;

import com.kbryant.quickcore.mvp.model.ModelHelper;

public class QuickPresenter extends Presenter implements IQuickPresenter {
    private ModelHelper modelHelper;

    public QuickPresenter(ModelHelper modelHelper) {
        this.modelHelper = modelHelper;
    }

    @Override
    public <T> T service(Class<T> serviceClass) {
        return modelHelper.getService(serviceClass);
    }

    @Override
    public ModelHelper modelHelper() {
        return modelHelper;
    }

    @Override
    public void detachView() {
        super.detachView();
        modelHelper.unBind();
    }
}
