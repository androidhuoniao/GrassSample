package com.grass.demo.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by grass on 16/7/31.
 */

public abstract class BaseRetrofit {

    public Retrofit getRetrofit() {
        Retrofit fit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return fit;
    }

    protected abstract String getBaseUrl();

}
