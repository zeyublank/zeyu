package com.dangzhongyang.zuoye.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ExpandableListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dangzhongyang.zuoye.R;
import com.dangzhongyang.zuoye.data.adapter.ErAdapter;
import com.dangzhongyang.zuoye.data.adapter.RecyclerAdapter;
import com.dangzhongyang.zuoye.data.beans.MBean;
import com.dangzhongyang.zuoye.data.callback.CallBack;
import com.dangzhongyang.zuoye.data.callback.M3CallBack;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends BaseFragment {


    @BindView(R.id.ex)
    ExpandableListView ex;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private CallBack callBack;
    private M3CallBack m3CallBack;


    public void setM3CallBack(M3CallBack m3CallBack) {
        this.m3CallBack = m3CallBack;
    }



    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }


    private List<MBean.BodyBean.ResultBean> result;

    @Override
    protected int getLayId() {
        return R.layout.fragment_second;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);

        if (getArguments() != null) {
            Bundle arguments = getArguments();
                result = (List<MBean.BodyBean.ResultBean>) arguments.getSerializable("key");

        }
        if (result.get(0).getChildren().size() != 0) {

            ErAdapter erAdapter = new ErAdapter(result, getContext());
            ex.setAdapter(erAdapter);
            ex.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    callBack.listenter(groupPosition, childPosition);
                    return true;
                }
            });
        } else {
            ex.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(R.layout.lie, result);
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
