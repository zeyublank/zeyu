package com.dangzhongyang.yuekaoa.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.dangzhongyang.yuekaoa.R;
import com.dangzhongyang.yuekaoa.data.adapter.FragmentAdapter;
import com.dangzhongyang.yuekaoa.data.beans.MusicBean;
import com.dangzhongyang.yuekaoa.data.callback.M3CallBack;
import com.dangzhongyang.yuekaoa.data.common.Constant;
import com.dangzhongyang.yuekaoa.model.MyModel;
import com.dangzhongyang.yuekaoa.presenter.MyContract;
import com.dangzhongyang.yuekaoa.presenter.MyPresenter;
import com.dangzhongyang.yuekaoa.view.fragment.LiuFragment;
import com.dangzhongyang.yuekaoa.view.fragment.SiFragment;
import com.dangzhongyang.yuekaoa.view.fragment.WuFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class Main3Activity extends BaseActivity<MyModel, MyPresenter> implements MyContract.View, M3CallBack {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager1)
    ViewPager viewPager1;

    List<Fragment> list = new ArrayList<>();
    List<String> strings = new ArrayList<>();
    @BindView(R.id.jiecao)
    JCVideoPlayerStandard jiecao;


    private List<MusicBean.BodyBean.ResultBean> result1;


    @Override
    protected void initView() {
        ButterKnife.bind(this);


        Intent intent = getIntent();
        int number = intent.getIntExtra("number", 0);
        Toast.makeText(this, number + "", Toast.LENGTH_SHORT).show();
        if (number != 0) {
            mPresenter.getMusicDatas(Constant.MBase_UEL + number + "/" + number);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main3;
    }

    @Override
    public void show(Object o) {

        MusicBean mBean = (MusicBean) o;
        System.out.println("===============" + mBean.getMessage());
        result1 = mBean.getBody().getResult();

         jiecao.setUp(result1.get(0).getUrl(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, result1.get(0).getName());

        strings.add("介绍");
        strings.add("目录");
        strings.add("评价");
        SiFragment firstFragment = new SiFragment();

        WuFragment secondFragment = new WuFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("number", 2);
        bundle.putSerializable("key", (Serializable) this.result1);
        secondFragment.setArguments(bundle);
        secondFragment.setM3CallBack(this);
        LiuFragment thirdFragment = new LiuFragment();
        list.add(firstFragment);
        list.add(secondFragment);
        list.add(thirdFragment);
        FragmentAdapter fragmentAdapter1 = new FragmentAdapter(getSupportFragmentManager(), list, strings);
        viewPager1.setAdapter(fragmentAdapter1);
        tabLayout.setupWithViewPager(viewPager1);

        viewPager1.setCurrentItem(1);
    }

    @Override
    public void errorMessage(String message) {
        Toast.makeText(this, "请求失败请稍后再试", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void clickListener(int position) {
        jiecao.setUp(result1.get(position).getUrl()
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, result1.get(position).getName());
    }


    @Override
    protected void onDestroy() {

        jiecao.releaseAllVideos();
        super.onDestroy();

    }
}
