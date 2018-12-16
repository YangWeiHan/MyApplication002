package com.example.recyclerview.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.recyclerview.Adapter.GridAdapter;
import com.example.recyclerview.Adapter.LinearAdapter;
import com.example.recyclerview.Bean.UserBean;
import com.example.recyclerview.DividerGridItemDecoration;
import com.example.recyclerview.R;

public class recycler_grid extends Fragment {
    //设置一行展示几个
    private final int mSpanCount = 3 ;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_grid,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView_grid);
        initView();

    }

    private void initView() {
        //  写一个 网格布局管理器
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),mSpanCount);
        //设置方向  这里是水平
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        recyclerView.setLayoutManager(gridLayoutManager);
        //实例化适配器
        final GridAdapter gridAdapter = new GridAdapter(getActivity());
        for (int i = 0; i < 12; i++) {
            UserBean userBean = new UserBean();
            // userBean.setName("sgdfsdf" + i);
            if(i == 0){
                userBean.setName("林深时见鹿");
                userBean.setImage(R.drawable.zn1);
            }else if (i == 1){
                userBean.setName("老树陪古屋");
                userBean.setImage(R.drawable.zn5);
            }else if (i == 2){
                userBean.setName("我遇见你");
                userBean.setImage(R.drawable.zn3);
            }else if (i == 3){
                userBean.setName("却没能让你留步");
                userBean.setImage(R.drawable.zn6);
            }else if (i == 4){
                userBean.setName("清晨时见雾");
                userBean.setImage(R.drawable.zn2);
            }else if (i == 5){
                userBean.setName("青草沾雨露");
                userBean.setImage(R.drawable.zn4);
            }else if (i == 6){
                userBean.setName("我爱上你");
                userBean.setImage(R.drawable.zn5);
            }else if (i == 7){
                userBean.setName("却没能把你留住");
                userBean.setImage(R.drawable.zn6);
            }else if (i == 8){
                userBean.setName("你别出现在我黎明的梦里");
                userBean.setImage(R.drawable.zn2);
            }else if (i == 9){
                userBean.setName("我怕我醒来就抱不到你");
                userBean.setImage(R.drawable.zn1);
            }else if (i == 10){
                userBean.setName("谁能给我麻木的酒");
                userBean.setImage(R.drawable.zn4);
            }else if(i == 11){
                userBean.setName("醒着醉 ");
            }
            gridAdapter.addItem(userBean);
        }
        //设置适配器
        recyclerView.setAdapter(gridAdapter);

        DividerGridItemDecoration dividerGridItemDecoration = new DividerGridItemDecoration(getActivity());
        recyclerView.addItemDecoration(dividerGridItemDecoration);

        //设置增加或者删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getActivity().findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"sdfd",Toast.LENGTH_LONG).show();
            UserBean userBean = new UserBean();
            userBean.setName("@妮儿");
            userBean.setImage(R.drawable.zn5);
            gridAdapter.adData(0,userBean);
            }
        });
        gridAdapter.setmClickListener(new GridAdapter.Click() {
            @Override
            public void OnClick(int i) {
                gridAdapter.removeDate(i);
            }

            @Override
            public void OnLongClick(int i) {

            }
        });

    }
}
