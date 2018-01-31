package com.kbryant.quickcore.sample.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(layout(),container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(savedInstanceState);
    }

    @SuppressWarnings("unchecked ")
    public <T extends View> T $(@IdRes int id){
        T target = null;
        if(getView()!=null){
            target  = (T) getView().findViewById(id);
        }
        return target;
    }
    @SuppressWarnings("unchecked ")
    public <T extends View> T $(@IdRes int id, View.OnClickListener onClickListener){
        T v = $(id);
        if(v!=null){
            v.setOnClickListener(onClickListener);
        }
        return v;
    }
    protected abstract void initView(Bundle savedInstanceState);
    protected abstract @LayoutRes
    int layout();
}
