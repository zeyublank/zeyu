package com.dangzhongyang.yuekaoa.view;

import android.content.Intent;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dangzhongyang.yuekaoa.R;
import com.dangzhongyang.yuekaoa.data.adapter.MyAdapter;
import com.dangzhongyang.yuekaoa.data.beans.Bean;
import com.dangzhongyang.yuekaoa.model.MyModel;
import com.dangzhongyang.yuekaoa.presenter.MyContract;
import com.dangzhongyang.yuekaoa.presenter.MyPresenter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MyModel, MyPresenter> implements MyContract.View {


    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        HashMap<String, String> map = new HashMap<>();
        map.put("orderOn", "");
        map.put("classtype", "031001004");
        map.put("forPeopleType", "");
        map.put("format", "");
        map.put("price", "");
        map.put("pageIndex", "1");
        map.put("pageSize", "10");
        map.put("classTag", "");

        mPresenter.getDatas(map);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void show(Object o) {
        Bean bean = (Bean) o;
        Toast.makeText(this, "bean.getBody().getResult().getData():" + bean.getBody().getResult().getData(), Toast.LENGTH_SHORT).show();
        System.out.println("========================" + bean);
        final List<Bean.BodyBean.ResultBean.DataBean> data = bean.getBody().getResult().getData();
        MyAdapter myAdapter = new MyAdapter(R.layout.item, data);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


                if (data.get(position).getClassTag() == 1) {
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("number",data.get(position).getID());
                    startActivity(intent);
                } else if (data.get(position).getClassTag() == 2) {
                    Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                    intent.putExtra("number",data.get(position).getID());
                    startActivity(intent);
                } else{
                    Toast.makeText(MainActivity.this, "此为收费请先开通会员", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void errorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }



}
