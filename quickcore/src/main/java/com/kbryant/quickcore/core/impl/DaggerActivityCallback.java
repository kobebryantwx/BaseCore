package com.kbryant.quickcore.core.impl;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kbryant.quickcore.core.HasDaggerApplication;
import com.kbryant.quickcore.core.HasDaggerInject;
import com.kbryant.quickcore.di.component.AppComponent;

public class DaggerActivityCallback<T> implements Application.ActivityLifecycleCallbacks {
    private AppComponent appComponent;
    private HasDaggerApplication<T> hasDaggerApplication;
    private T call;
    public DaggerActivityCallback(HasDaggerApplication<T> hasDaggerApplication,AppComponent appComponent){
        this.hasDaggerApplication = hasDaggerApplication;
        this.appComponent = appComponent;
    }
    @Override
    @SuppressWarnings("unchecked")
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.e(getClass().getSimpleName(),"-onActivityCreated");
        if(activity instanceof HasDaggerInject){
            HasDaggerInject<T> callActivity = (HasDaggerInject<T>) activity;
            call = hasDaggerApplication.activityComponent(appComponent);
            callActivity.inject(call);
        }
        DaggerFragmentCallback<T> daggerFragmentCallback = new DaggerFragmentCallback<>(call);
        ((AppCompatActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(daggerFragmentCallback,true);
    }


    @Override
    public void onActivityStarted(Activity activity) {


    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
