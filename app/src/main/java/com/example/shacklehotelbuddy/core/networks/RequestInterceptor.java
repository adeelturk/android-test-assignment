package com.example.shacklehotelbuddy.core.networks;

import androidx.annotation.NonNull;

import com.example.shacklehotelbuddy.BuildConfig;


import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original
                .newBuilder()
                .header("X-RapidAPI-Key", BuildConfig.X_RAPID_API_KEY)
                .header("X-RapidAPI-Host", BuildConfig.X_RAPID_API_HOST)
                .header("content-type", "application/json")
                .method(original.method(), original.body());
        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }


}
