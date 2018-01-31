package com.kbryant.quickcore.mvp;

import android.app.Activity;
import android.support.v4.app.Fragment;

public interface IPresenter {
    void attachView(Activity activity);
    void attachView(Fragment fragment);
    <T> T view(Class<T> tClass);
    <T> T viewActivity(Class<T> tClass);
    <T> T viewFragment(Class<T> tClass);
    void detachView();
}
