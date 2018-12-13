package com.example.okhttp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.okhttp.Bean.UserBean;
import com.example.okhttp.R;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;

    private Context context;
    private List<UserBean.DataNews> list;

    public NewsAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setList(List<UserBean.DataNews> mlist) {
        this.list = list;
        if (list != null){
            this.list = mlist;
        }
        notifyDataSetChanged();
    }

    public void addList(List<UserBean.DataNews> mlist) {
        if (list != null){
            list.addAll(mlist);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_ONE){
            View view = LayoutInflater.from(context).inflate(R.layout.item_one,viewGroup,false);
            return new ViewHolderONE(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_two,viewGroup,false);
            return new ViewHolderTWO(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        switch (type){
            case TYPE_ONE:
               ViewHolderONE viewHolderONE = (ViewHolderONE) viewHolder;
               viewHolderONE.textView.setText(list.get(i).getCreatetime());
               break;
            case TYPE_TWO:
            ViewHolderTWO viewHolderTWO = (ViewHolderTWO) viewHolder;
            viewHolderTWO.textView.setText(list.get(i).getName());
            Glide.with(context).load(list.get(i).getIcon()).into(viewHolderTWO.imageView);
            break;
            default:break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 1){
            return TYPE_ONE;
        }else {
            return TYPE_TWO;
        }
    }
    class ViewHolderONE extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolderONE(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.createtime);
        }
    }
    class ViewHolderTWO extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public ViewHolderTWO(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.icon);
            textView = itemView.findViewById(R.id.name);
        }
    }
}
