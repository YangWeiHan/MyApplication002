package com.example.yangweihan1213.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yangweihan1213.R;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;;

import java.util.Map;

public class ShowActivity extends AppCompatActivity {
        private ImageView imageView;
        private TextView textView;
        private Button button,show_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获得UMShareAPI实例
                UMShareAPI umShareAPI =  UMShareAPI.get(ShowActivity.this);
                umShareAPI.getPlatformInfo(ShowActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override//开始
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override//成功
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        //获取名字
                        String name  = map.get("screen_name");
                        //获取头像
                        String img  = map.get("profile_image_url");

                        textView.setText(name);
                        Glide.with(ShowActivity.this).load(img).into(imageView);

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });
            }
        });
    }
    /**
     * 添加回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    private void initView() {
        textView = findViewById(R.id.title_name);
        imageView = findViewById(R.id.image_icon);
        button = findViewById(R.id.button_login);
       findViewById(R.id.botton_show).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                startActivity(new Intent(ShowActivity.this,GoodActivity.class));
           }
       });


    }



}
