package com.grass.demo.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by grass on 16/7/30.
 */
//  http://www.ncuhome.cn/NewIndex2013/AjaxGetList.ashx?partID=394&pageindex=1&pagesize=2
public interface NcuService {

    @GET("/NewIndex2013/AjaxGetList.ashx?partID=394&pageindex=1")
    public Call<List<News>> getNews(@Query("pagesize") int pagesize);
}
