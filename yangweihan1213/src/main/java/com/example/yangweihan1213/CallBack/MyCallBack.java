package com.example.yangweihan1213.CallBack;

public interface MyCallBack<T> {

    void setData(T data);
    void setFail(String msg);

}
