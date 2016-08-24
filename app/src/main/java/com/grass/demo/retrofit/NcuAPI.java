package com.grass.demo.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by grass on 16/7/30.
 */
public class NcuAPI {

    private NcuService mService;
    private static final String BASE_URL = "http://www.ncuhome.cn";

    public NcuAPI() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = retrofit.create(NcuService.class);
    }

    public Call<List<News>> getNews(int pagesize) {
        return mService.getNews(pagesize);
    }

}
