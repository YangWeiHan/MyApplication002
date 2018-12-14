package com.example.yangweihan1213.Model;

import com.example.yangweihan1213.Bean.XiangqingBean;
import com.example.yangweihan1213.CallBack.ICallBack;
import com.example.yangweihan1213.CallBack.MyCallBack;
import com.example.yangweihan1213.OkHttp.OkHttpUlit;

import java.io.IOException;

public class IModellmpl implements IModel{


    @Override
    public void requesrData(String uil, Class clazz, final MyCallBack myCallBack) {
        OkHttpUlit.getmInstance().getEnqueue(uil, clazz, new ICallBack() {
            @Override
            public void setData(Object o) {
                myCallBack.setData(o);
            }

            @Override
            public void setFail(IOException msg) {

            }
        });
    }

    @Override
    public void requesrDataXianqing(String uil, Class clazz, final MyCallBack myCallBack) {
        OkHttpUlit.getmInstance().getEnqueue(uil, XiangqingBean.class, new ICallBack() {
            @Override
            public void setData(Object o) {
                myCallBack.setData(o);
            }

            @Override
            public void setFail(IOException msg) {

            }
        });
    }
}
