package com.kbryant.quickcore.sample.host.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kbryant.quickcore.core.HasDaggerInject;
import com.kbryant.quickcore.sample.ActivityComponent;
import com.kbryant.quickcore.sample.host.MainContract;
import com.kbryant.quickcore.sample.host.MainPresenter;

import javax.inject.Inject;

import com.kbryant.quickcore.sample.base.BaseFragment;

import me.militch.quickcore.sample.R;

public class TestFragment extends BaseFragment implements View.OnClickListener,MainContract.InnerView,HasDaggerInject<ActivityComponent> {
    private TextView tv;
    private Button btn;
    @Inject
    MainPresenter presenter;
    public static TestFragment newInstance() {
        Bundle args = new Bundle();
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int layout() {
        return R.layout.page_test;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        presenter.attachView(this);
        tv = $(R.id.test_tv);
        btn = $(R.id.inner_btn);
        tv.setText("Fragment1");
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        presenter.testFragment();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.detachView();
    }

    @Override
    public void showToastMsg(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
}
