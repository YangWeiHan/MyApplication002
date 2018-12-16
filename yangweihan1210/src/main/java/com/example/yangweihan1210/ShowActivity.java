package com.example.yangweihan1210;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yangweihan1210.Adapter.FragmentAdpater;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabs;
    private List<Fragment> list;
    private FragmentPagerAdapter fd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        viewPager = findViewById(R.id.viewpager);
        tabs = findViewById(R.id.tabs);
        list = new ArrayList<>();
        fd = new FragmentAdpater(getSupportFragmentManager(), this, list);
        viewPager.setAdapter(fd);
        tabs.setupWithViewPager(viewPager);
    }
}
