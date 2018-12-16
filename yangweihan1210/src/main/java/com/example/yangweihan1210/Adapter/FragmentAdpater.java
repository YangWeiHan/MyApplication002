package com.example.yangweihan1210.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yangweihan1210.BannerFragment;

import java.util.List;

public class FragmentAdpater extends FragmentPagerAdapter {
    private Context context;
    private List<Fragment> mlist;
    private String[] tabs = new String[]{
            "主页", "其他"
    };

    public FragmentAdpater(FragmentManager fm, Context context, List<Fragment> mlist) {
        super(fm);
        this.context = context;
        this.mlist = mlist;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new BannerFragment();

            default:
                return new  Fragment();
        }
    }
}
