package com.kbryant.quickcore.mvp.presenter;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.kbryant.quickcore.mvp.IPresenter;

import java.lang.reflect.Type;
import java.util.Arrays;

public class Presenter implements IPresenter {
    protected Activity activity;
    protected Fragment fragment;

    @Override
    public void attachView(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void attachView(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public <T> T view(Class<T> tClass) {
        T t = viewActivity(tClass);
        if (t == null) {
            t = viewFragment(tClass);
        }
        return t;
    }

    @Override
    public <T> T viewActivity(Class<T> tClass) {
        T t = null;
        if (activity != null) {
            //判断指定的类是否为目标类所实现的接口
            Type[] types = activity.getClass().getGenericInterfaces();
            boolean exists = Arrays.asList(types).contains(tClass);
            if (exists) {
                t = tClass.cast(activity);
            }
        }
        return t;
    }

    @Override
    public <T> T viewFragment(Class<T> tClass) {
        T t = null;
        if (fragment != null) {
            //判断指定的类是否为目标类所实现的接口
            Type[] types = fragment.getClass().getGenericInterfaces();
            boolean exists = Arrays.asList(types).contains(tClass);
            if (exists) {
                t = tClass.cast(fragment);
            }
        }
        return t;
    }

    @Override
    public void detachView() {
        this.activity = null;
        this.fragment = null;
    }
}
