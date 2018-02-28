package com.kbryant.quickcore.sample.service;

import com.kbryant.quickcore.sample.API;
import com.kbryant.quickcore.sample.bean.UserInfo;
import com.kbryant.quickcore.util.RespBase;

import io.reactivex.Flowable;
import quickcore.annotation.Repository;
import retrofit2.http.GET;

@Repository
public interface MainService {
    @GET(API.TEST)
    Flowable<RespBase<UserInfo>> requestTest();
}

