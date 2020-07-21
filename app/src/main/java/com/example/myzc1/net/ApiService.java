package com.example.myzc1.net;

import com.example.myzc1.bean.DatasBean;
import com.example.myzc1.bean.JsonBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String BASE_URL="https://www.wanandroid.com/";

    @GET("project/list/1/json?cid=294")
    Observable<JsonBean> getJson();
}
