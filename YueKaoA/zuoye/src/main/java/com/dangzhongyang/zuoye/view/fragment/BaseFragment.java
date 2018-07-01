package com.dangzhongyang.zuoye.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dangzhongyang.zuoye.data.utils.TUtils;
import com.dangzhongyang.zuoye.model.BaseModel;
import com.dangzhongyang.zuoye.presenter.BasePresenter;
import com.dangzhongyang.zuoye.view.BaseView;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment<M extends BaseModel,P extends BasePresenter> extends Fragment {
    public M mModel;
    public P mPresenter;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(getLayId(), container, false);
        mModel= TUtils.getT(this,0);
        mPresenter= TUtils.getT(this,1);
        if (this instanceof BaseView) {
            mPresenter.setM(mModel,this);

        }
        return view;
    }

    protected abstract int getLayId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        init();
    }

    protected abstract void init();

    protected abstract void initView(View view);
}
