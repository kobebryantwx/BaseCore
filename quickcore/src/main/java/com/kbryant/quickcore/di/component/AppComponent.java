package com.kbryant.quickcore.di.component;

import com.kbryant.quickcore.core.impl.AppTarget;

import javax.inject.Singleton;

import dagger.Component;

import com.kbryant.quickcore.di.module.GlobalConfigModule;
import com.kbryant.quickcore.di.module.HttpConfigModule;
import com.kbryant.quickcore.repository.impl.RepositoryStore;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
@Singleton
@Component(modules = {HttpConfigModule.class,GlobalConfigModule.class})
public interface AppComponent {
    OkHttpClient okhttp();
    Retrofit retrofit();
    RepositoryStore repositoryStore();
    void inject(AppTarget appTarget);
}
