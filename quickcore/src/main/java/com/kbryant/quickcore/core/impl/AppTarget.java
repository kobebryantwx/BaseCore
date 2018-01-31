package com.kbryant.quickcore.core.impl;

import android.app.Application;
import javax.inject.Inject;

import com.kbryant.quickcore.core.HasDaggerApplication;
import com.kbryant.quickcore.core.Target;
import com.kbryant.quickcore.di.component.AppComponent;

import com.kbryant.quickcore.di.component.DaggerAppComponent;
import com.kbryant.quickcore.repository.impl.RepositoryStore;

public class AppTarget implements Target {
    private AppComponent appComponent;
    private DaggerActivityCallback<?> daggerActivityCallback;
    @Inject
    RepositoryStore repositoryStore;
    @SuppressWarnings("unchecked")
    private <T>AppTarget(Application application){

        if(application instanceof HasDaggerApplication){
            HasDaggerApplication<T> hasDaggerApplication = (HasDaggerApplication<T>) application;
            appComponent = DaggerAppComponent.builder().
                    globalConfigModule(hasDaggerApplication.setupGlobalConfigModule()).
                    httpConfigModule(hasDaggerApplication.setupHttpConfigModule()).build();
            appComponent.inject(this);
            daggerActivityCallback = new DaggerActivityCallback<T>(hasDaggerApplication,appComponent);
            application.registerActivityLifecycleCallbacks(daggerActivityCallback);
        }
    }
    public static AppTarget create(Application application){
        return new AppTarget(application);
    }
    @Override
    public AppComponent getAppComponent() {
        return appComponent;
    }
}
