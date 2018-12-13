package com.example.okhttp.IVew;
/*
* 抽取VIew的接口
* */
public interface IVew<T> {
    /*
    * 请求成功返回的数据
    * */
    void showResponseData(T data);
    void success(String msg);
    void fail(String msg);
}
