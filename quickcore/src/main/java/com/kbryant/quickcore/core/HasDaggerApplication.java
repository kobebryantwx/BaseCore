package com.kbryant.quickcore.core;


import com.kbryant.quickcore.di.component.AppComponent;
import com.kbryant.quickcore.di.module.GlobalConfigModule;
import com.kbryant.quickcore.di.module.HttpConfigModule;

public interface HasDaggerApplication<T> {
    GlobalConfigModule setupGlobalConfigModule();
    HttpConfigModule setupHttpConfigModule();
    T activityComponent(AppComponent appComponent);
}
