package com.dangzhongyang.zuoye.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.dangzhongyang.zuoye.R;
import com.dangzhongyang.zuoye.data.adapter.FragmentAdapter;
import com.dangzhongyang.zuoye.data.beans.MBean;
import com.dangzhongyang.zuoye.data.callback.CallBack;
import com.dangzhongyang.zuoye.data.callback.M3CallBack;
import com.dangzhongyang.zuoye.data.common.Constant;
import com.dangzhongyang.zuoye.model.MyModel;
import com.dangzhongyang.zuoye.presenter.MyContract;
import com.dangzhongyang.zuoye.presenter.MyPresenter;
import com.dangzhongyang.zuoye.view.fragment.FirstFragment;
import com.dangzhongyang.zuoye.view.fragment.SecondFragment;
import com.dangzhongyang.zuoye.view.fragment.ThirdFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class Main2Activity extends BaseActivity<MyModel, MyPresenter> implements MyContract.View, CallBack, M3CallBack {
    @BindView(R.id.jiecao)
    JCVideoPlayerStandard jiecao;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private int tag;
    private List<MBean.BodyBean.ResultBean> result;
    List<Fragment> list = new ArrayList<>();
    List<String> strings = new ArrayList<>();
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        tag = intent.getIntExtra("tag", 0);
        int number = intent.getIntExtra("number", 0);
        if (number != 0) {
            mPresenter.getMDatas(Constant.MBase_UEL + number + "/" + number);
        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;

    }
    @Override
    public void show(Object o) {
        MBean mBean = (MBean) o;
        System.out.println("==============="+mBean.getMessage());
        result = mBean.getBody().getResult();
        if(tag==1){
            jiecao.TOOL_BAR_EXIST = false;
            jiecao.setUp(result.get(0).getChildren().get(0).getUrl()
                    , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, result.get(0).getChildren().get(0).getName());
        }else {
            jiecao.setUp(result.get(0).getUrl(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, result.get(0).getName());
        }
        strings.add("介绍");
        strings.add("目录");
        strings.add("评价");
        FirstFragment firstFragment = new FirstFragment();

        SecondFragment secondFragment = new SecondFragment();
        Bundle bundle = new Bundle();

        bundle.putSerializable("key", (Serializable) result);
        secondFragment.setArguments(bundle);
        secondFragment.setCallBack(this);
        secondFragment.setM3CallBack(this);
        ThirdFragment thirdFragment = new ThirdFragment();
        list.add(firstFragment);
        list.add(secondFragment);
        list.add(thirdFragment);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), list, strings);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);

    }

    @Override
    public void errorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void listenter(int groupPosition, int chilPosition) {
        jiecao.setUp(result.get(groupPosition).getChildren().get(chilPosition).getUrl()
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, result.get(groupPosition).getChildren().get(chilPosition).getName());
    }

    @Override
    public void clickListener(int position) {
        jiecao.setUp(result.get(position).getUrl()
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, result.get(position).getName());
    }
    @Override
    protected void onDestroy() {

        jiecao.releaseAllVideos();
        super.onDestroy();

    }
}
