package com.grass.demo.retrofit.DoubanMovie;

import com.grass.demo.retrofit.BaseRetrofit;

/**
 * Created by grass on 16/7/31.
 */

public class DoubanTop250MovieRetrofit extends BaseRetrofit {
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }
}
