package com.grass.demo.retrofit;

import com.orhanobut.logger.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by grass on 16/7/30.
 */

public class TrendingPresenter {

    private static final String END_POINT = "http://githubtrending.herokuapp.com/";

    public void loadData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TrendingService trendingService = retrofit.create(TrendingService.class);
        trendingService.getTrendingRepos().enqueue(new Callback<TrendingResultResp>() {
            @Override
            public void onResponse(Call<TrendingResultResp> call, Response<TrendingResultResp> response) {
                Logger.i("grass", "onResponse is Success: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<TrendingResultResp> call, Throwable t) {
                Logger.i("grass", "onFailure is working");
            }
        });
    }
}
