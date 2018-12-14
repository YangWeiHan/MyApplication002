package com.example.yangweihan1213.Activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangweihan1213.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;

    /**
     * ValueAnimator  先改变值，然后 手动赋值 给对象的属性从而实现动画；是 间接 对对象属性进行操作；
     * ObjectAnimator 先改变值，然后 自动赋值 给对象的属性从而实现动画；是 直接 对对象属性进行操作；
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        textView = findViewById(R.id.hellow_world);

        textView.setOnClickListener(this);
        textView.setText("点击老子进去首页");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.hellow_world :
            Toast.makeText(this, textView + "123", Toast.LENGTH_LONG).show();
               /* ObjectAnimator animator = ObjectAnimator.ofFloat(textView,"translationY",50,500,50,500);
                //设置时间
                animator.setDuration(5000);
                //重复次数
                animator.setRepeatCount(-1);
                //开始
                animator.start();
                break;*/
                //Y轴平移
                ObjectAnimator translation_Y = ObjectAnimator.ofFloat(textView,"translationY",50,500,50,500,100);
                //透明度
                ObjectAnimator alpha = ObjectAnimator.ofFloat(textView, "alpha", 0f, 2f);
                //X轴缩放
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(textView, "scaleX", 0f, 1f);
                //Y轴缩放
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(textView, "scaleY", 0f, 1f);
                //旋转
                ObjectAnimator rotation = ObjectAnimator.ofFloat(textView, "rotation", 1f, 3600f);
                //动画一起执行
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(3000);
                animatorSet.playTogether( translation_Y,alpha,scaleX,scaleY);

                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override//开始
                    public void onAnimationStart(Animator animation) {
                        Toast.makeText(MainActivity.this,"开始 ",Toast.LENGTH_LONG).show();
                    }
                            //结束
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startActivity(new Intent(MainActivity.this,ShowActivity.class));
                    }
                            //取消
                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override//重复
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animatorSet.start();
                default:break;
        }
    }
}
