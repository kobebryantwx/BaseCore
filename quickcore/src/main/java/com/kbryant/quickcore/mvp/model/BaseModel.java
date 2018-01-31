package com.kbryant.quickcore.mvp.model;

import com.kbryant.quickcore.mvp.IModel;
import com.kbryant.quickcore.repository.IRepositoryStore;
import com.kbryant.quickcore.repository.impl.RepositoryStore;

public abstract class BaseModel implements IModel {
    private IRepositoryStore repositoryStore;
    public BaseModel(RepositoryStore repositoryStore){
        this.repositoryStore = repositoryStore;
    }
    @Override
    public IRepositoryStore store(){
        return repositoryStore;
    }
}
