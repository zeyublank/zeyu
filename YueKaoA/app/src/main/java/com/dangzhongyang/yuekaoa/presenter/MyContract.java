package com.dangzhongyang.yuekaoa.presenter;

import com.dangzhongyang.yuekaoa.data.beans.Bean;
import com.dangzhongyang.yuekaoa.data.beans.MBean;
import com.dangzhongyang.yuekaoa.data.beans.MusicBean;
import com.dangzhongyang.yuekaoa.model.BaseModel;
import com.dangzhongyang.yuekaoa.view.BaseView;

import java.util.HashMap;

import rx.Observable;

public class MyContract {

    public interface View extends BaseView {
      
    }

    public interface Model extends BaseModel {
          Observable<Bean> getDatas(HashMap<String,String> map);
          Observable<MBean> getMDatas(String url);
        Observable<MusicBean> getMusicDatas(String url);
    }

    public abstract static class Presenter extends BasePresenter<Model, View> {
        public abstract void getDatas(HashMap<String,String> map);
        public abstract void getMDatas(String url);
        public abstract void getMusicDatas(String url);

    }
}