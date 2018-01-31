package com.kbryant.quickcore.sample.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout());
        initView(savedInstanceState);
    }
    protected abstract void initView(Bundle savedInstanceState);
    protected abstract @LayoutRes int layout();
    @SuppressWarnings("unchecked")
    public final  <T extends View> T $(@IdRes int id){
        return (T) findViewById(id);
    }
    @SuppressWarnings("unchecked ")
    public final  <T extends View> T $(@IdRes int id, View.OnClickListener onClickListener){
        T v = $(id);
        v.setOnClickListener(onClickListener);
        return v;
    }

    /**
     * 替换Fragment
     * @param containerView 视图id
     * @param fragment Fragment对象
     */
    protected final void replacef(@IdRes int containerView, Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(containerView,fragment);
        ft.commit();
    }
}
