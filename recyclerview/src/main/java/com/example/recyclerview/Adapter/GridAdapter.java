package com.example.recyclerview.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.recyclerview.Bean.UserBean;
import com.example.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 继承RecyclerView.Adapter<LinearAdapter.ViewHolder>
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
        private List<UserBean> list;
        private Context context;

    public GridAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }
    //相当于上下文  Content


        public void addItem(UserBean userBean){
            if (userBean != null){
                list.add(userBean);
                notifyDataSetChanged();
            }
        }
    public void adData(int i,UserBean userBean){
        if (userBean != null){
            list.add(0,userBean);
            notifyItemInserted(i);
            notifyItemRangeChanged(i,list.size());
        }
    }
    /**
     * 静态内部类 ViewHolder
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        //列出所有需要用到的控件
        private final TextView name;
        private final ImageView image;
        //写一个构造方法，找到所有的控件（就是获取资源ID）
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_linear);
            image = itemView.findViewById(R.id.image_linear);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull GridAdapter.ViewHolder viewHolder, int position) {
        //获取索引值
        UserBean userBean = list.get(position);
        viewHolder.name.setText(userBean.getName());

        Glide.with(context).load(userBean.getImage()).into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @NonNull
    @Override
    public GridAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_grid_item,viewGroup,false);
        return new ViewHolder(view);
    }
    /*
    * 移除数据
    * */
    public void removeDate(int i){
        list.remove(i);
        //刷新
        notifyItemRemoved(i);
        notifyItemRangeRemoved(i,list.size());
    }

    Click mClick;

    public void setmClickListener(Click Click) {
        this.mClick = Click;
    }

    public interface Click{
        void OnClick(int i);
        void OnLongClick(int i);
    }
}
