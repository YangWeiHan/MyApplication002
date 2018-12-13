package com.example.okhttp.Presenter;

import java.util.Map;

/*
* P层的接口
* 里面简单的封装了一个开始请求网络   开始做任务
* */
public interface IPresenter {

    void  startRequest(String url, Class clazz);
   /* //  Map 集合用户存储照片
    void  startUpLoading(String url,Map<String,String>map , Class clazz);*/
}
