package com.kbryant.quickcore.execute.impl;

import com.kbryant.quickcore.mvp.model.ModelHelper;
import com.kbryant.quickcore.mvp.view.AbsListView;

public class ModelAndListView<T> extends ModelAndView<AbsListView<T>> {
    private ModelAndListView(AbsListView<T> view, ModelHelper modelHelper) {
        super(view, modelHelper);
    }
    public static <T> ModelAndListView<T> create(AbsListView<T> view,ModelHelper modelHelper){
        return new ModelAndListView<T>(view,modelHelper);
    }
}
