package com.kbryant.quickcore.sample.host.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.kbryant.quickcore.sample.host.MainContract;

import java.util.List;

import com.kbryant.quickcore.sample.base.BaseFragment;
import com.kbryant.quickcore.sample.bean.UserInfo;

import me.militch.quickcore.sample.R;

public class Test2Fragment extends BaseFragment implements MainContract.View {
    private TextView textView;
    private MainContract.Presenter mainPresenter;
    public static Test2Fragment newInstance() {
        Bundle args = new Bundle();
        Test2Fragment fragment = new Test2Fragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int layout() {
        return R.layout.page_test;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        textView = $(R.id.test_tv);
        textView.setText("Fragment2");
    }

    @Override
    public void showToastMsg(String msg) {

    }

    @Override
    public void bindData(List<UserInfo> data) {

    }
}
