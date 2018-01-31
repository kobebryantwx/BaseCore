package com.kbryant.quickcore.repository;

import java.util.List;

public interface IRepositoryStore {
    void addRetrofitService(Class<?>... services);
    void addRetrofitService(List<Class> services);
    <T> T getRetrofitService(Class<T> serviceClass);
}
