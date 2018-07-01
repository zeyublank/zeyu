package com.dangzhongyang.zuoye.presenter;

import com.dangzhongyang.zuoye.data.beans.Bean;
import com.dangzhongyang.zuoye.data.beans.MBean;

import java.util.HashMap;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
* Created by TMVPHelper on 2018/07/01
*/
public class MyPresenter extends MyContract.Presenter{
    @Override
    public void getDatas(HashMap<String, String> map) {
        Observable<Bean> datas = m.getDatas(map);
        datas
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bean>() {
                    @Override
                    public void call(Bean bean) {

                        System.out.println("===============" + bean.getBody().getResult().getData());
                        v.show(bean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        v.errorMessage(throwable.getMessage());
                    }
                });
    }

    @Override
    public void getMDatas(String url) {
        Observable<MBean> mDatas = m.getMDatas(url);
        mDatas
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MBean>() {
                    @Override
                    public void call(MBean mBean) {
                        v.show(mBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        v.errorMessage(throwable.getMessage());
                    }
                });
    }
}