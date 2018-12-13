package com.example.okhttp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.okhttp.Adapter.NewsAdapter;
import com.example.okhttp.Bean.UserBean;
import com.example.okhttp.IVew.IVew;
import com.example.okhttp.Presenter.IPresenterlmlp;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IVew {
    private IPresenterlmlp iPresenterlmlp;
    private XRecyclerView xRecyclerView;
    private NewsAdapter adapter;
    private int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化P层的实现类
        iPresenterlmlp = new IPresenterlmlp(this);

        iPresenterlmlp.startRequest(APIs.showDataUrl,UserBean.class);

        //开始请求数据
        initView();
        //加载布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置方向
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        index = 0;
        //实例化Adapter
        adapter = new NewsAdapter(this);
        //设置适配器
        xRecyclerView.setAdapter(adapter);
        //设置允许上拉加载  下拉刷新
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setPullRefreshEnabled(true);
        //设置 监听事件
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                index = 0;
                iPresenterlmlp.startRequest(APIs.showDataUrl,UserBean.class);
            }
            @Override
            public void onLoadMore() {
                index = 1;
                iPresenterlmlp.startRequest(APIs.showDataUrl,UserBean.class);
            }
        });

    }


    private void initView() {
        xRecyclerView = findViewById(R.id.XRecyclerView);
    }

    @Override
    public void showResponseData(Object data) {
        UserBean bean = (UserBean) data;
        List<UserBean.DataNews> list = bean.getData();
        Toast.makeText(this,list.get(0).getName(),Toast.LENGTH_SHORT).show();
        if (index == 1){
            adapter.addList(list);
            xRecyclerView.loadMoreComplete();
        }else {
            adapter.setList(list);
            xRecyclerView.refreshComplete();
        }
    }

    @Override
    public void success(String msg) {

    }

    @Override
    public void fail(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
