package com.example.recyclerview;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.recyclerview.Adapter.FragmentAdapter;

public class MainActivity extends AppCompatActivity {
        private ViewPager main_viewPager;
        private TabLayout main_tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_viewPager = findViewById(R.id.main_viewpager);
        main_tabLayout = findViewById(R.id.main_tabLayout);

        main_viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));

        main_tabLayout.setupWithViewPager(main_viewPager);
    }
}
