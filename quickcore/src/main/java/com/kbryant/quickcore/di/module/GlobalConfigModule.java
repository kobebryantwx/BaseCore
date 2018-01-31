package com.kbryant.quickcore.di.module;

import android.app.Application;

import com.kbryant.quickcore.repository.IRepositoryStoreHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import com.kbryant.quickcore.repository.impl.RepositoryStore;
import retrofit2.Retrofit;

/**
 * 全局配置Module
 */
@Module
public class GlobalConfigModule {
    private Application application;
    private List<Class> services;
    private IRepositoryStoreHelper iRepositoryStoreHelper;

    private GlobalConfigModule(GlobalConfigModule.Builder builder) {
        this.application = builder.application;
        this.iRepositoryStoreHelper = builder.iRepositoryStoreHelper;
        this.services = builder.services;
    }

    public static Builder builder(Application application) {
        return new Builder(application);
    }

    @Provides
    @Singleton
    RepositoryStore provideRepositoryStore(Retrofit retrofit) {
        RepositoryStore repositoryStore = new RepositoryStore(retrofit);
        if (iRepositoryStoreHelper != null) {
            iRepositoryStoreHelper.setRepository(repositoryStore);
        }
        if (services != null) {
            repositoryStore.addRetrofitService(services);
        }
        return repositoryStore;
    }

    public static class Builder {
        Application application;
        List<Class> services;
        private IRepositoryStoreHelper iRepositoryStoreHelper;

        private Builder(Application application) {
            this.application = application;
            services = new ArrayList<>();
        }

        public Builder addServices(List<Class> services) {
            this.services.addAll(services);
            return this;
        }

        public Builder addServices(Class... service) {
            return addServices(Arrays.asList(service));
        }

        public Builder setRepositoryStoreHelper(IRepositoryStoreHelper iRepositoryStoreHelper) {
            this.iRepositoryStoreHelper = iRepositoryStoreHelper;
            return this;
        }

        public GlobalConfigModule build() {
            return new GlobalConfigModule(this);
        }
    }
}
