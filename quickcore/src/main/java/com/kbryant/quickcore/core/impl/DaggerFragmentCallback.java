package com.kbryant.quickcore.core.impl;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.kbryant.quickcore.core.HasDaggerInject;

public class DaggerFragmentCallback<T> extends FragmentManager.FragmentLifecycleCallbacks {
    private T call;

    public DaggerFragmentCallback(T call) {
        this.call = call;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onFragmentAttached(FragmentManager fm, Fragment f, Context context) {
        super.onFragmentAttached(fm, f, context);
        if (f instanceof HasDaggerInject) {
            HasDaggerInject<T> hasDaggerInject = (HasDaggerInject<T>) f;
            hasDaggerInject.inject(call);
        }
    }
}
