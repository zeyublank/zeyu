package com.dangzhongyang.yuekaoa.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.dangzhongyang.yuekaoa.R;
import com.dangzhongyang.yuekaoa.data.adapter.FragmentAdapter;
import com.dangzhongyang.yuekaoa.data.beans.MBean;
import com.dangzhongyang.yuekaoa.data.callback.CallBack;
import com.dangzhongyang.yuekaoa.data.common.Constant;
import com.dangzhongyang.yuekaoa.model.MyModel;
import com.dangzhongyang.yuekaoa.presenter.MyContract;
import com.dangzhongyang.yuekaoa.presenter.MyPresenter;
import com.dangzhongyang.yuekaoa.view.fragment.FirstFragment;
import com.dangzhongyang.yuekaoa.view.fragment.SecondFragment;
import com.dangzhongyang.yuekaoa.view.fragment.ThirdFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class Main2Activity extends BaseActivity<MyModel, MyPresenter> implements MyContract.View, CallBack {


    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    List<Fragment> list = new ArrayList<>();
    List<String> strings = new ArrayList<>();
    @BindView(R.id.jiecao)
    JCVideoPlayerStandard jiecao;
    private List<MBean.BodyBean.ResultBean> result;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();

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

        jiecao.TOOL_BAR_EXIST = false;
        jiecao.setUp(result.get(0).getChildren().get(0).getUrl()
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, result.get(0).getChildren().get(0).getName());


        strings.add("介绍");
        strings.add("目录");
        strings.add("评价");
        FirstFragment firstFragment = new FirstFragment();

        SecondFragment secondFragment = new SecondFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("number",1);
        bundle.putSerializable("key", (Serializable) result);
        secondFragment.setArguments(bundle);
        secondFragment.setCallBack(this);
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

        Toast.makeText(this, "请求失败请稍后再试", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void listenter(int groupPosition, int chilPosition) {
        //jiecao.resetProgressAndTime();

        jiecao.setUp(result.get(groupPosition).getChildren().get(chilPosition).getUrl()
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, result.get(groupPosition).getChildren().get(chilPosition).getName());
    }
}
