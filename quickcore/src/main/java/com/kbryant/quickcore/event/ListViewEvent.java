package com.kbryant.quickcore.event;

import java.util.List;

import com.kbryant.quickcore.mvp.view.AbsListView;

/**
 * 集合数据返回类
 *
 * @param <T>
 */
public class ListViewEvent<T> implements ViewEvent<AbsListView<T>, List<T>> {
    @Override
    public void call(AbsListView<T> view, List<T> data) {
        view.bindData(data);
    }
}
