package com.kbryant.quickcore.sample.host;

import com.kbryant.quickcore.event.ViewEvent;
import com.kbryant.quickcore.execute.impl.ModelAndView;
import com.kbryant.quickcore.mvp.model.ModelHelper;

import javax.inject.Inject;

import com.kbryant.quickcore.mvp.presenter.QuickPresenter;
import com.kbryant.quickcore.sample.bean.UserInfo;
import com.kbryant.quickcore.sample.service.MainService;
import com.kbryant.quickcore.util.ApiException;

public final class MainPresenter extends QuickPresenter implements MainContract.Presenter {
    @Inject
    MainPresenter(ModelHelper modelHelper) {
        super(modelHelper);
    }

    @Override
    public void testActivity() {
        ModelAndView.create(view(MainContract.View.class), modelHelper())
                .request(service(MainService.class).requestTest(), new ViewEvent<MainContract.View, UserInfo>() {
                    @Override
                    public void call(MainContract.View view, UserInfo data) {
                        view.showToastMsg("这是Activity");
                    }
                }, new ViewEvent<MainContract.View, ApiException>() {
                    @Override
                    public void call(MainContract.View view, ApiException data) {
                        view.showToastMsg(data.getMessage());
                    }
                });
    }

    @Override
    public void testFragment() {
        MainContract.InnerView view = view(MainContract.InnerView.class);
        view.showToastMsg("这是Fragment");
    }
}
