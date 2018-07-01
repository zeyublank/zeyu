package com.dangzhongyang.yuekaoa.data.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangzhongyang.yuekaoa.R;
import com.dangzhongyang.yuekaoa.data.beans.MBean;
import com.dangzhongyang.yuekaoa.data.beans.MusicBean;

import java.util.List;

public class RecyclerAdapter extends BaseQuickAdapter<MusicBean.BodyBean.ResultBean,BaseViewHolder> {
    public RecyclerAdapter(int layoutResId, @Nullable List<MusicBean.BodyBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MusicBean.BodyBean.ResultBean item) {
        helper.setText(R.id.name,item.getName());
        int partDuration = item.getPartDuration();
        int time = partDuration / 60;
        helper.setText(R.id.time,time+"分钟");
        helper.setText(R.id.size,item.getPartSize()+"M");

    }
}
