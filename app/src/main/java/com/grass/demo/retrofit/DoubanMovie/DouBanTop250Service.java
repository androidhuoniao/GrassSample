package com.grass.demo.retrofit.DoubanMovie;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by grass on 16/7/31.
 */

// https://api.douban.com/v2/movie/top250?start=0&count=10

public interface DouBanTop250Service {

    @GET("top250")
    Observable<DoubanTop250MovieResponse> getDoubanMovies(@Query("start") int start, @Query("count") int count);
}
