package com.dangzhongyang.zuoye.model;

import com.dangzhongyang.zuoye.data.beans.Bean;
import com.dangzhongyang.zuoye.data.beans.MBean;
import com.dangzhongyang.zuoye.data.common.Constant;
import com.dangzhongyang.zuoye.http.api.ApiService;
import com.dangzhongyang.zuoye.presenter.MyContract;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
* Created by TMVPHelper on 2018/07/01
*/
public class MyModel implements MyContract.Model {
    @Override
    public Observable<Bean> getDatas(HashMap<String, String> map) {
        ApiService apiService = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constant.BASE_UEL)
                .build()
                .create(ApiService.class);
        Observable<Bean> post = apiService.getPost(map);
        return post;
    }

    @Override
    public Observable<MBean> getMDatas(String url) {
        ApiService apiService = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constant.MBase_UEL)
                .build()
                .create(ApiService.class);
        Observable<MBean> request = apiService.getRequest(url);
        return request;
    }

}