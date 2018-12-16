package com.example.recyclerview_moreitem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class XRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<UserBean> list ;

    public XRecyclerAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }
    /*
       分类
        * */
        private static final int TYPE_ONE = 0;
        private static final int TYPE_TWD = TYPE_ONE+ 1;

    public void setList(List<UserBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        //根据类型的不同，加载不同的布局，返回不同的holder
        if (viewType == TYPE_ONE){
            View v = View.inflate(context,R.layout.item_recycler_one,null);
            return new ViewHolderOne(v);
        }else {
            View v = View.inflate(context,R.layout.item_recycler_two,null);
            return new ViewHolderTwo(v);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //根据角标，调用getItemViewType方法，取出类型
        int type = getItemViewType(i);
        switch (type){
            case TYPE_ONE:
                ViewHolderOne holderOne = (ViewHolderOne) viewHolder;
                Glide.with(context).load(R.drawable.zn4).into(holderOne.imageView);
                holderOne.textView.setText(list.get(i).getTitle());
                break;
            case TYPE_TWD:
                ViewHolderTwo holderTwo = (ViewHolderTwo) viewHolder;
                Glide.with(context).load(R.drawable.zn2).into(holderTwo.imageView);
                holderTwo.textView.setText(list.get(i).getTitle());
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        UserBean userBean = list.get(position);
        if (userBean.getType() == TYPE_ONE){
            return  TYPE_ONE;
        }else {
            return TYPE_TWD;
        }
    }
    /*
    * 有几个条目 写几个viewHolder
    * */
    class ViewHolderOne extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;
        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_one);
            imageView = itemView.findViewById(R.id.image_one);
        }
    }

    class ViewHolderTwo extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;
        public ViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_two);
            imageView = itemView.findViewById(R.id.image_two);
        }
    }

}
