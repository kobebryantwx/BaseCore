package com.kbryant.quickcore.sample.host;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kbryant.quickcore.core.HasDaggerInject;
import com.kbryant.quickcore.sample.ActivityComponent;

import java.util.List;

import javax.inject.Inject;

import com.kbryant.quickcore.sample.base.BaseActivity;
import com.kbryant.quickcore.sample.bean.UserInfo;
import com.kbryant.quickcore.sample.host.fragment.TestFragment;

import me.militch.quickcore.sample.R;

public class MainActivity extends BaseActivity implements MainContract.View, HasDaggerInject<ActivityComponent>, View.OnClickListener {

    private Button button;

    @Inject
    MainPresenter mainPresenter;

    @Override
    public void onClick(View v) {
        mainPresenter.testActivity();
    }

    @Override
    public int layout() {
        return R.layout.activity_test;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mainPresenter.attachView(this);
        button = $(R.id.text_btn, this);
        TestFragment fragment = TestFragment.newInstance();
        replacef(R.id.test_content, fragment);
    }

    @Override
    public void showToastMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    @Override
    public void bindData(List<UserInfo> data) {
        showToastMsg("--数据来了" + data.get(0).getName());
    }

    @Override
    public void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
}
