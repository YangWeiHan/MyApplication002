package com.example.yangweihan1213.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangweihan1213.APis.Apis;
import com.example.yangweihan1213.Adapter.ShowDataAdapter;
import com.example.yangweihan1213.Bean.UserBean;
import com.example.yangweihan1213.Presenter.IPresenterlmpl;
import com.example.yangweihan1213.R;
import com.example.yangweihan1213.View.IVIew;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class GoodActivity extends AppCompatActivity implements IVIew {
        private EditText goods_name;
        private TextView goods_show,goods_zong,goods_num,goods_price,goods_shai;
        private XRecyclerView xRecyclerView;
        private IPresenterlmpl iPresenterlmpl;
        private String type = "手机";
        private int pager = 1;
        private boolean p;
        private ShowDataAdapter adapter;
        private final int mSpanCount = 2;
        private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good);
        initView();
        //实例化 P层 实现类
        iPresenterlmpl =new IPresenterlmpl(this);
        //拼接字符串
        iPresenterlmpl.startRequest(String.format(Apis.ShowDataurl,type,pager),UserBean.class);
        //管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(GoodActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        //搜索按钮

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = goods_name.getText().toString();
                iPresenterlmpl.startRequest(String.format(Apis.ShowDataurl,s,pager),UserBean.class);
            }
        });

        //按销量排序
        goods_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresenterlmpl.startRequest(Apis.xiaoDataurl,UserBean.class);
            }
        });
        //按照价格排序
        goods_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresenterlmpl.startRequest(Apis.priceDataurl,UserBean.class);
            }
        });



        //管理器
       goods_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p == true){
                    //线性布局
                    Toast.makeText(GoodActivity.this,"这个是线性布局！！！",Toast.LENGTH_SHORT).show();
                    goods_show.setText("网格");
                    LinearLayoutManager layoutManager = new LinearLayoutManager(GoodActivity.this);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    xRecyclerView.setLayoutManager(layoutManager);
                    p =false;
                }else {
                    Toast.makeText(GoodActivity.this,"这个是网格布局！！！",Toast.LENGTH_SHORT).show();
                    goods_show.setText("线性");
                    p = true;
                    //网格布局
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(GoodActivity.this,mSpanCount);
                    gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                    xRecyclerView.setLayoutManager(gridLayoutManager);
                }
            }
        });


        //实例适配器
        adapter = new ShowDataAdapter(GoodActivity.this);

        //设置适配器
        xRecyclerView.setAdapter(adapter);
        //设置xRecyclerView  支持上拉刷新 下拉加载
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);

        adapter.setOnItemClickListener(new ShowDataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pasition) {
                startActivity(new Intent(GoodActivity.this,XiangqingActivity.class));
            }
        });

        adapter.setOnItemLongClickListener(new ShowDataAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int pasition,String a) {
                Toast.makeText(GoodActivity.this,a,Toast.LENGTH_SHORT).show();
            }
        });

        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                pager = 1;
                iPresenterlmpl.startRequest(String.format(Apis.ShowDataurl,type,pager),UserBean.class);
            }

            @Override
            public void onLoadMore() {
                pager++;
                iPresenterlmpl.startRequest(String.format(Apis.ShowDataurl,type,pager),UserBean.class);
            }
        });
    }
    //获取资源ID
    private void initView() {
        goods_name = findViewById(R.id.goods_name);
        goods_show = findViewById(R.id.goods_show);
        goods_zong = findViewById(R.id.goods_zong);
        goods_num = findViewById(R.id.goods_num);
        goods_price = findViewById(R.id.goods_price);
        goods_shai = findViewById(R.id.goods_shai);
        xRecyclerView = findViewById(R.id.xrecycview);
        button = findViewById(R.id.shousuo);
    }

    //数据请求成功
    @Override
    public void setRequestData(Object data) {
        UserBean userBean = (UserBean) data;
        //添加到当前页面的一个集合里
        List<UserBean.DataBean> list = userBean.getData();
      //  Toast.makeText(GoodActivity.this,list.get(0).getTitle(),Toast.LENGTH_LONG).show();
        if (pager == 1){
            adapter.setMdata(list);
            //刷新停止
            xRecyclerView.refreshComplete();
        }else {
            //加载停止
            adapter.addMdata(list);
            xRecyclerView.loadMoreComplete();
        }
    }
}
