package com.dangzhongyang.zuoye.http.api;

import com.dangzhongyang.zuoye.data.beans.Bean;
import com.dangzhongyang.zuoye.data.beans.MBean;

import java.util.HashMap;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

public interface ApiService {
    @FormUrlEncoded
    @POST("getCourseByTypeAndMore?")
    Observable<Bean> getPost(@FieldMap HashMap<String,String> map);
    @GET()
    Observable<MBean>getRequest(@Url String url);


}
