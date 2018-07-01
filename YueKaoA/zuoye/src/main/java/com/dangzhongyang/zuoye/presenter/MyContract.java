package com.dangzhongyang.zuoye.presenter;

import com.dangzhongyang.zuoye.data.beans.Bean;
import com.dangzhongyang.zuoye.data.beans.MBean;
import com.dangzhongyang.zuoye.model.BaseModel;
import com.dangzhongyang.zuoye.view.BaseView;

import java.util.HashMap;

import rx.Observable;

public class MyContract {

 public interface View extends BaseView {

 }

 public interface Model extends BaseModel {
  Observable<Bean> getDatas(HashMap<String,String> map);
  Observable<MBean> getMDatas(String url);

 }

 public abstract static class Presenter extends BasePresenter<Model, View> {
  public abstract void getDatas(HashMap<String,String> map);
  public abstract void getMDatas(String url);


 }
}