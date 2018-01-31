package com.kbryant.quickcore.di.module;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HttpConfigModule {
    private String host;
    private int connectTimeout;
    private int readTimeout;
    private int writeTimeout;
    private List<Interceptor> interceptors;

    private HttpConfigModule(HttpConfigModule.Builder builder) {
        this.host = builder.host;
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.writeTimeout = builder.writeTimeout;
        this.interceptors = builder.interceptors;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * OkHttp客户端提供者
     *
     * @return OkHttp客户端
     */
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(connectTimeout, TimeUnit.SECONDS);
        builder.readTimeout(readTimeout, TimeUnit.SECONDS);
        builder.writeTimeout(writeTimeout, TimeUnit.SECONDS);
        if (interceptors != null) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }
        return builder.build();
    }

    /**
     * Retrofit提供者
     *
     * @param okHttpClient OkHttp客户端
     * @return Retrofit
     */
    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(host);
        builder.client(okHttpClient);
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

    public static final class Builder {
        private String host;//请求主机地址
        private int connectTimeout = 10;//请求连接超时时间（单位：秒）
        private int readTimeout = 10;//请求读取数据超时时间（单位：秒）
        private int writeTimeout = 10;//请求写入数据超时时间（单位：秒）
        private List<Interceptor> interceptors;

        private Builder() {
        }

        public HttpConfigModule.Builder setHost(String host) {
            this.host = host;
            return this;
        }

        public HttpConfigModule.Builder setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public HttpConfigModule.Builder setReadTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public HttpConfigModule.Builder setWriteTimeout(int writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        public HttpConfigModule.Builder addInterceptor(Interceptor interceptor) {
            if (interceptors == null) {
                interceptors = new ArrayList<>();
            }
            this.interceptors.add(interceptor);
            return this;
        }

        public HttpConfigModule build() {
            return new HttpConfigModule(this);
        }
    }
}
