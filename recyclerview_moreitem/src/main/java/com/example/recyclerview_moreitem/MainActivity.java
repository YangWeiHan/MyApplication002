package com.example.recyclerview_moreitem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int index = 0;
    private XRecyclerView xRecyclerView;
    private XRecyclerAdapter adapter;
    private List<UserBean> userBeanList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        xRecyclerView = findViewById(R.id.xRecyclerView);
        //管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);

        addFakeData();
        //把集合里的数据添加到适配器
        adapter = new XRecyclerAdapter(this);
        adapter.setList(userBeanList);
        xRecyclerView.setAdapter(adapter);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);

        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                userBeanList.clear();
                index = 0;
                addFakeData();

                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                addFakeData();

                xRecyclerView.loadMoreComplete();

            }
        });

    }
    /*
    * shuju
    * */
    private void addFakeData(){
        for (int j = index; index < j + 20; index++){
            UserBean userBean = new UserBean();
            userBean.setType(index%2);
            userBean.setTitle("@张妮儿⁄(⁄ ⁄•⁄ω⁄•⁄ ⁄)⁄"+index);

            userBeanList.add(userBean);
        }
    }

}
