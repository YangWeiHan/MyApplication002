package com.example.recyclerview.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.recyclerview.Adapter.LinearAdapter;
import com.example.recyclerview.Bean.UserBean;
import com.example.recyclerview.R;

public class recycler_linear extends Fragment {
    private ImageView imageView;
    private TextView textView;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.recycler_linear,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView_linear);
            initView();
    }

    private void initView() {
       //  写一个 线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //设置方向  这里是垂直
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //实例化适配器
        LinearAdapter linearAdapter = new LinearAdapter(getActivity());
        for (int i = 0; i < 10; i++) {
            UserBean userBean = new UserBean();
           // userBean.setName("sgdfsdf" + i);
            if(i == 0){
                userBean.setName("在没风的地方找太阳");
                userBean.setImage(R.drawable.zn6);
            }else if (i == 1){
                userBean.setName("在你冷的地方做暖阳");
                userBean.setImage(R.drawable.zn5);
            }else if (i == 2){
                userBean.setName("人事纷纷");
                userBean.setImage(R.drawable.zn4);
            }else if (i == 3){
                userBean.setName("你总是太天真");
                userBean.setImage(R.drawable.zn3);
            }else if (i == 4){
                userBean.setName("往后的余生");
                userBean.setImage(R.drawable.zn2);
            }else if (i == 5){
                userBean.setName("往后余生\n" +
                        "风雪是你");
                userBean.setImage(R.drawable.zn1);
            }else if (i == 6){
                userBean.setName("春华是你");
                userBean.setImage(R.drawable.zn6);
            }else if (i == 7){
                userBean.setName("夏雨也是你\n" +
                        "秋黄是你");
                userBean.setImage(R.drawable.zn5);
            }else if (i == 8){
                userBean.setName("四季冷暖是你");
                userBean.setImage(R.drawable.zn4);
            }else if (i == 9){
                userBean.setName("目光所致\n" +
                        "也是你");
                userBean.setImage(R.drawable.zn3);
            }
            linearAdapter.addItem(userBean);
        }
        //设置适配器
        recyclerView.setAdapter(linearAdapter);
        //设置分割线
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        //添加自定义分割线
        divider.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.recycler_divider_horizontal));
        recyclerView.addItemDecoration(divider);


    }

}
