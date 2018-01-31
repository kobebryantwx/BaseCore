package com.kbryant.quickcore.sample.host;

import com.kbryant.quickcore.mvp.model.BaseModel;
import com.kbryant.quickcore.repository.impl.RepositoryStore;
import com.kbryant.quickcore.sample.bean.UserInfo;
import com.kbryant.quickcore.sample.service.MainService;
import com.kbryant.quickcore.util.RXUtil;
import com.kbryant.quickcore.util.RespBase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class MainModel extends BaseModel {
    @Inject
    public MainModel(RepositoryStore repositoryStore) {
        super(repositoryStore);
    }

    public Flowable<List<UserInfo>> getTest() {
        MainService mainService = store().getRetrofitService(MainService.class);
        return mainService.requestTest().compose(RXUtil.<RespBase<List<UserInfo>>>rxSchedulerHelper()).compose(RXUtil.<List<UserInfo>>handleRespBaseResult());
    }
}
