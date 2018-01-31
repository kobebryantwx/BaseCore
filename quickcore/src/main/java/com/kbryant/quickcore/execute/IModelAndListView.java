package com.kbryant.quickcore.execute;

import com.kbryant.quickcore.event.ViewEvent;

import java.util.List;

import io.reactivex.Flowable;
import com.kbryant.quickcore.event.ListViewEvent;
import com.kbryant.quickcore.mvp.view.AbsListView;
import com.kbryant.quickcore.util.ApiException;
import com.kbryant.quickcore.util.RespBase;

public interface IModelAndListView<T>{
    void requestList(Flowable<RespBase<List<T>>> respResult, ListViewEvent<T> okVet, ViewEvent<AbsListView<T>,ApiException> badVet);
}
