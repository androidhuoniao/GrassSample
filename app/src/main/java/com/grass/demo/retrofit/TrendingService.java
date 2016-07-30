package com.grass.demo.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mingjun on 16/7/20.
 */
public interface TrendingService {

    @GET("trending?languages[]=java&languages[]=objective-c&languages[]=bash&languages[]=python&languages[]=html")
    Call<TrendingResultResp> getTrendingRepos();

}
