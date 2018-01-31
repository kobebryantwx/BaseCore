package com.kbryant.quickcore.sample.host;

import com.kbryant.quickcore.mvp.model.ModelHelper;

import javax.inject.Inject;

import com.kbryant.quickcore.mvp.presenter.QuickPresenter;

public final class MainPresenter extends QuickPresenter implements MainContract.Presenter {
    @Inject
    MainPresenter(ModelHelper modelHelper) {
        super(modelHelper);
    }


    @Override
    public void testActivity() {
        MainContract.View view = view(MainContract.View.class);
        view.showToastMsg("这是Activity");
    }

    @Override
    public void testFragment() {
        MainContract.InnerView view = view(MainContract.InnerView.class);
        view.showToastMsg("这是Fragment");
    }
}
