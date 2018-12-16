package com.example.recyclerview.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.recyclerview.Fragment.recycle_staggered;
import com.example.recyclerview.Fragment.recycler_grid;
import com.example.recyclerview.Fragment.recycler_linear;

public class FragmentAdapter extends FragmentPagerAdapter {

    //设置数据
    private String[] list = new String[]{
      "线性布局","网格布局","瀑布流"
    };

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list[position];
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
               return new recycler_linear();
            case 1:
               return new recycler_grid();
            case 2:
               return new recycle_staggered();
            default: return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return list.length;
    }
}
