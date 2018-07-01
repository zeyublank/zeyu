package com.dangzhongyang.yuekaoa.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dangzhongyang.yuekaoa.R;
import com.dangzhongyang.yuekaoa.data.adapter.RecyclerAdapter;
import com.dangzhongyang.yuekaoa.data.beans.MBean;
import com.dangzhongyang.yuekaoa.data.beans.MusicBean;
import com.dangzhongyang.yuekaoa.data.callback.M3CallBack;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WuFragment extends BaseFragment {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private List<MusicBean.BodyBean.ResultBean> result1;
    private M3CallBack m3CallBack;

    public void setM3CallBack(M3CallBack m3CallBack) {
        this.m3CallBack = m3CallBack;
    }

    @Override
    protected int getLayId() {
        return R.layout.fragment_wu;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            Bundle arguments = getArguments();

                result1 = (List<MusicBean.BodyBean.ResultBean>) arguments.getSerializable("key");
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(R.layout.lie, result1);
            recyclerView.setAdapter(recyclerAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    m3CallBack.clickListener(position);
                }
            });
        }


    }




}
