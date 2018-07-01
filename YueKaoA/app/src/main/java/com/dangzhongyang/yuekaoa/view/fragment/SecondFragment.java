package com.dangzhongyang.yuekaoa.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dangzhongyang.yuekaoa.R;
import com.dangzhongyang.yuekaoa.data.adapter.ErAdapter;
import com.dangzhongyang.yuekaoa.data.adapter.RecyclerAdapter;
import com.dangzhongyang.yuekaoa.data.beans.MBean;
import com.dangzhongyang.yuekaoa.data.beans.MusicBean;
import com.dangzhongyang.yuekaoa.data.callback.CallBack;
import com.dangzhongyang.yuekaoa.data.callback.M3CallBack;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends BaseFragment {


    private CallBack callBack;
    private M3CallBack m3CallBack;
    private List<MusicBean.BodyBean.ResultBean> result1;

    public void setM3CallBack(M3CallBack m3CallBack) {
        this.m3CallBack = m3CallBack;
    }

    private RecyclerView recyclerView;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    private ExpandableListView ex;
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
        ex = view.findViewById(R.id.ex);
        recyclerView = view.findViewById(R.id.recyclerView);
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            int number = arguments.getInt("number");
            if (number == 1) {
                result = (List<MBean.BodyBean.ResultBean>) arguments.getSerializable("key");
                System.out.println("=============" + result.get(1).getName());
            } else {
                result1 = (List<MusicBean.BodyBean.ResultBean>) arguments.getSerializable("key");
            }

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
