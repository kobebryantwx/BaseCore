package com.kbryant.quickcore.util;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
public class SimpleInterceptor implements Interceptor{
    private static final String TAG = "QuickCore:Response>>";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        Log.d(TAG ,response.body().string());
        return response;
    }
}
