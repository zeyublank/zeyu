package com.dangzhongyang.yuekaoa.data.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> list;
    List<String>strings;
    public FragmentAdapter(FragmentManager fm,List<Fragment> list,List<String>strings) {
        super(fm);
        this.list=list;
        this.strings=strings;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}
