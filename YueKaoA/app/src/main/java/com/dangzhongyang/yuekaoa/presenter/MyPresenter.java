package com.dangzhongyang.yuekaoa.presenter;

import com.dangzhongyang.yuekaoa.data.beans.Bean;
import com.dangzhongyang.yuekaoa.data.beans.MBean;
import com.dangzhongyang.yuekaoa.data.beans.MusicBean;

import java.util.HashMap;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by TMVPHelper on 2018/06/29
 */
public class MyPresenter extends MyContract.Presenter {

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

    @Override
    public void getMusicDatas(String url) {
        Observable<MusicBean> mDatas = m.getMusicDatas(url);
        mDatas
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MusicBean>() {
                    @Override
                    public void call(MusicBean mBean) {
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