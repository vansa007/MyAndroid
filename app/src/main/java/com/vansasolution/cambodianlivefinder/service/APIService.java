package com.vansasolution.cambodianlivefinder.service;

import com.vansasolution.cambodianlivefinder.model.ResD;
import com.vansasolution.cambodianlivefinder.model.Restaruant;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Query;

/**
 * Created by asus on 1/24/2017.
 */

public interface APIService {
//    @GET("/api/restaurant/search-rest")
//    Call<List<Restaruant>> getRestaruantLocation(@Query("category_id") String name, @Query("page") String page
//            , @Query("limit") String limit);

    @GET("/api/restaurant/search-rest")
    Call<Restaruant> getRestaruantLocation();
}
