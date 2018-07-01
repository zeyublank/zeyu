package com.dangzhongyang.zuoye.data.adapter;

import android.support.annotation.Nullable;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangzhongyang.zuoye.R;
import com.dangzhongyang.zuoye.data.beans.MBean;

import java.util.List;


public class RecyclerAdapter extends BaseQuickAdapter<MBean.BodyBean.ResultBean,BaseViewHolder> {
    public RecyclerAdapter(int layoutResId, @Nullable List<MBean.BodyBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MBean.BodyBean.ResultBean item) {
        helper.setText(R.id.name,item.getName());
        int partDuration = item.getPartDuration();
        int time = partDuration / 60;
        helper.setText(R.id.time,time+"分钟");
        helper.setText(R.id.size,item.getPartSize()+"M");

    }
}
