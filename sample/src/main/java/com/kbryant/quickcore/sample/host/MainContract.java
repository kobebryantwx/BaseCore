package com.kbryant.quickcore.sample.host;

import com.kbryant.quickcore.mvp.BaseView;
import com.kbryant.quickcore.mvp.view.AbsListView;
import com.kbryant.quickcore.mvp.IPresenter;
import com.kbryant.quickcore.sample.bean.UserInfo;

public interface MainContract {
    interface View extends AbsListView<UserInfo>{
    }
    interface InnerView extends BaseView{

    }
    interface Presenter extends IPresenter {
        void testActivity();
        void testFragment();
    }

}
