package com.example.yangweihan1213.Model;

import com.example.yangweihan1213.CallBack.MyCallBack;

public interface IModel {
    void requesrData(String uil, Class clazz, MyCallBack myCallBack);

    void requesrDataXianqing(String uil, Class clazz, MyCallBack myCallBack);
}
