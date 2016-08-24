package com.grass.demo.retrofit.DoubanMovie;

import java.util.List;

/**
 * Created by grass on 16/7/31.
 */

public class DoubanTop250MovieResponse {

    public int count;
    public int start;
    public int total;
    public String title;

    public List<DoubanMovie> subjects;

}
