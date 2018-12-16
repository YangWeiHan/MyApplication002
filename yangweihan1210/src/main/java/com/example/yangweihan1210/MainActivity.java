package com.example.yangweihan1210;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yangweihan1210.Bean.UserBean;
import com.example.yangweihan1210.IPresenter.IPresenterleml;
import com.example.yangweihan1210.View.IView;

public class MainActivity extends AppCompatActivity implements IView {
    private EditText edit_mobel,edit_password;
    private Button button_login;
    private IPresenterleml iPresenterleml;
    private String url = "http://www.zhaoapi.cn/user/login?mobile=%s&password=%s";
    //"http://www.zhaoapi.cn/user/login?mobile=18146548015&password=123456";
    private String password;
    private String mobel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_mobel = findViewById(R.id.edit_mobel);
        edit_password = findViewById(R.id.edit_password);
        button_login = findViewById(R.id.button_login);
        iPresenterleml = new IPresenterleml(this);

        //登录点击事件
        button_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //得到输入框的值
                mobel = edit_mobel.getText().toString();
                password = edit_password.getText().toString();
                iPresenterleml.startResqusert1(String.format(url, mobel, password));
               // checkPermission();
            }
        });
    }
        private void checkPermission(){
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET},100);
            }else {
                startResult();
            }
        }
        private void startResult(){
            //获取路径

        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            startResult();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iPresenterleml != null) {
            iPresenterleml.onDatech();
        }
    }

    @Override
    public void showResponesData(Object data) {
        UserBean userBean = (UserBean)data;
        Toast.makeText(this,""+userBean.getMsg(),Toast.LENGTH_LONG).show();
       if (userBean.getMsg().equals("登录成功")){
           startActivity(new Intent(MainActivity.this,ShowActivity.class));
       }
    }

    @Override
    public void success(String msg) {


    }

    @Override
    public void fail(String msg) {

    }
}
