package com.kbryant.quickcore.di.module;

import android.app.Activity;

import com.kbryant.quickcore.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Activity activity;
    public ActivityModule(Activity activity) {
        this.activity = activity;
    }
    @Provides @ActivityScope
    Activity provideActivity(){
        return activity;
    }
}
