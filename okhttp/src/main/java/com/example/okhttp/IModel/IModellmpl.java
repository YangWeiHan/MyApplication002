package com.example.okhttp.IModel;

import com.example.okhttp.MyCallBack.MyCallBack;
import com.example.okhttp.OKHttp.ICallBack;
import com.example.okhttp.OKHttp.OkHttpUtil;

import java.util.Map;
/**
 * IModel接口实现类
 * 执行网络请求，对返回的数据进行处理
 */
public class IModellmpl implements IModel {
    /**
     * 处理请求数据
     * param url 请求的url地址
     * param  如果为post请求，则传入post参数
     * param callBack 回调
     */


    @Override
    public void requestData(String url, Class clazz, final MyCallBack callBack) {
        OkHttpUtil.getInstance().getEngqueue(url, new ICallBack() {
            @Override
            public void success(Object obj) {
                callBack.setData(obj);
            }

            @Override
            public void failed(Exception e) {

            }
        }, clazz);
    }
}
