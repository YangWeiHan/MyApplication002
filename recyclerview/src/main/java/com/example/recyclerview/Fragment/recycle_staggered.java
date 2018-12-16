package com.example.recyclerview.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerview.Adapter.GridAdapter;
import com.example.recyclerview.Adapter.StaggeredAdapter;
import com.example.recyclerview.Bean.UserBean;
import com.example.recyclerview.DividerGridItemDecoration;
import com.example.recyclerview.R;

public class recycle_staggered extends Fragment {
    private final int mSpanCount = 2;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_staggered,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview_Staggered);
        initView();

    }

    private void initView() {
        //使用瀑布流布局,第一个参数 spanCount 一行几个,第二个参数 orentation 排列方向
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(mSpanCount,StaggeredGridLayoutManager.VERTICAL);
        //设置布局管理器
        recyclerView.setLayoutManager(gridLayoutManager);
        //实例化适配器
        StaggeredAdapter staggeredAdapter = new StaggeredAdapter(getActivity());
        int[] avatarArray = new int[]{R.drawable.zn1,R.drawable.zn2,R.drawable.zn3};
        for (int i = 0; i < 30; i++) {
            UserBean userBean = new UserBean();

           userBean.setName("清仓甩卖，仅需￥88");
           userBean.setImage(avatarArray[i % avatarArray.length]);
           staggeredAdapter.addItem(userBean);
        }
        //设置适配器
        recyclerView.setAdapter(staggeredAdapter);
        DividerGridItemDecoration dividerGridItemDecoration = new DividerGridItemDecoration(getActivity());
        recyclerView.addItemDecoration(dividerGridItemDecoration);
    }

}

