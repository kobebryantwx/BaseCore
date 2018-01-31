package com.kbryant.quickcore.sample;

import com.kbryant.quickcore.di.component.AppComponent;

import dagger.Component;
import com.kbryant.quickcore.di.ActivityScope;
import com.kbryant.quickcore.sample.host.MainActivity;
import com.kbryant.quickcore.sample.host.fragment.TestFragment;

@ActivityScope
@Component(dependencies = AppComponent.class)
public interface ActivityComponent{
    void inject(MainActivity activity);
    void inject(TestFragment testFragment);
}
