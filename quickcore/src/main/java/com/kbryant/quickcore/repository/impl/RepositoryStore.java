package com.kbryant.quickcore.repository.impl;

import com.kbryant.quickcore.repository.IRepositoryStore;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class RepositoryStore implements IRepositoryStore {
    private Retrofit retrofit;
        private HashMap<Class,Object> retrofitServices = new HashMap<>();
    @Inject
    public RepositoryStore(Retrofit retrofit){
        this.retrofit = retrofit;
    }
    @Override
    public void addRetrofitService(Class<?>... services) {
        for(Class service : services){
            retrofitServices.put(service,retrofit.create(service));
        }
    }

    @Override
    public void addRetrofitService(List<Class> services) {
        for(Class service : services){
            retrofitServices.put(service,retrofit.create(service));
        }
    }

    @Override
    @SuppressWarnings("unchecked ")
    public <T> T getRetrofitService(Class<T> serviceClass) {
        return (T) retrofitServices.get(serviceClass);
    }
}
