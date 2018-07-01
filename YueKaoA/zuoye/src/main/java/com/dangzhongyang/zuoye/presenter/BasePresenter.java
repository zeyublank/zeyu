package com.dangzhongyang.zuoye.presenter;

public class BasePresenter<M, V> {
    public M m;
    public V v;

    public void setM(M m,V v) {
        this.m = m;
        this.v = v;
    }


}
