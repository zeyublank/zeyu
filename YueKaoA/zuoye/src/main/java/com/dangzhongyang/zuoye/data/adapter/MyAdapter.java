package com.dangzhongyang.zuoye.data.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.dangzhongyang.zuoye.R;
import com.dangzhongyang.zuoye.data.beans.Bean;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends BaseQuickAdapter<Bean.BodyBean.ResultBean.DataBean,BaseViewHolder> {
    public MyAdapter(int layoutResId, @Nullable List<Bean.BodyBean.ResultBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Bean.BodyBean.ResultBean.DataBean item) {
        helper.setText(R.id.name,item.getClassName());
        helper.setText(R.id.message,item.getClassRecommend());
        helper.setText(R.id.message,item.getClassRecommend());
        helper.setText(R.id.number,item.getStudyNum()+"");
        Picasso.with(mContext).load(item.getClassCoverPic()).placeholder(R.mipmap.ic_launcher).into((ImageView)helper.getView(R.id.img));
        if(item.getClassTag()==3){
            helper.setText(R.id.mian,"收费");
        }else {
            helper.setText(R.id.mian,"免费");
        }

    }
}
