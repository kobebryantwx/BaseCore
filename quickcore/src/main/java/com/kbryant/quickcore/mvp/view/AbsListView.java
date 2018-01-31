package com.kbryant.quickcore.mvp.view;

import com.kbryant.quickcore.mvp.BaseView;

import java.util.List;

public interface AbsListView<T> extends BaseView {
    void bindData(List<T> data);
}
