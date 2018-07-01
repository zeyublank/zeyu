package com.dangzhongyang.yuekaoa.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.BaseMenuPresenter;

import com.dangzhongyang.yuekaoa.data.utils.TUtils;
import com.dangzhongyang.yuekaoa.model.BaseModel;
import com.dangzhongyang.yuekaoa.presenter.BasePresenter;

public abstract class BaseActivity<M extends BaseModel,P extends BasePresenter> extends AppCompatActivity {
    public M mModel;
    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel= TUtils.getT(this,0);
        mPresenter= TUtils.getT(this,1);
        if (this instanceof BaseView) {
            mPresenter.setM(mModel,this);

        }
        setContentView(getLayoutId());
        initView();
    }

    protected abstract void initView();

    protected abstract int getLayoutId();
}
