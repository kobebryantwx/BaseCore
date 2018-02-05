package com.kbryant.quickcore.sample;

import android.app.Application;

import com.kbryant.quickcore.core.impl.AppTarget;
import com.kbryant.quickcore.di.component.AppComponent;
import com.kbryant.quickcore.core.HasDaggerApplication;
import com.kbryant.quickcore.di.module.GlobalConfigModule;
import com.kbryant.quickcore.di.module.HttpConfigModule;
import com.kbryant.quickcore.repository.AutoInjectRepository;


public class MyApplication extends Application implements HasDaggerApplication<ActivityComponent> {
    //对象实例
    private static MyApplication instance;

    /**
     * 获取对象实例
     *
     * @return 对象实例
     */
    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppTarget.create(this);
        instance = this;
    }

    @Override
    public GlobalConfigModule setupGlobalConfigModule() {
        //全局配置信息，设置Service仓库
        return GlobalConfigModule
                .builder(this)
                .setRepositoryStoreHelper(new AutoInjectRepository())
                .build();
    }

    @Override
    public HttpConfigModule setupHttpConfigModule() {
        //HTTP请求配置信息
        return HttpConfigModule.builder()
                .setHost(API.HOST).build();
    }

    @Override
    public ActivityComponent activityComponent(
            AppComponent appComponent) {
        //Activity注入器配置
        return DaggerActivityComponent.builder()
                .appComponent(appComponent).build();
    }
}
