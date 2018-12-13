package com.example.okhttp.OKHttp;

import android.os.Looper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtil {

    private static volatile OkHttpUtil mInstance;
    private final OkHttpClient mClient;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    /**
     * 第一步，写一个单例，这里用的懒汉式，也可以使用饿汉
     * @return
     */
    public static OkHttpUtil getInstance(){
        if (mInstance == null){
            synchronized (OkHttpUtil.class){
                if (null == mInstance){
                    mInstance = new OkHttpUtil();
                }
            }
        }
        return mInstance;
    }
    /*第二步：  完成构造方法
    * 完成构造方法 OkHttpClient
    * */
    private OkHttpUtil(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        /**
         * 使用构造者(Builder)模式
         * 设置连接超时
         * 读取超时
         * 写超时
         * 添加拦截器
         */
        //   添加拦截器
        mClient = new OkHttpClient.Builder()
            .connectTimeout(10,TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
            .writeTimeout(10,TimeUnit.SECONDS)
               //   添加拦截器
            .addInterceptor(interceptor)
            .build();
    }
    /**
     * 网络同步方法
     * 创建一个get请求
     * 设置一个路径
     *
     * 通过mClient.newCall建一个Call对象
     * 调用同步请求方法
     *
     * @param url
     * @param callBack
     * @param clazz
     * @return
     * @throws IOException
     */
   /* public String getExecute(String url , ICallBack callBack,Class clazz) throws IOException {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = mClient.newCall(request);
        Response response = call.execute();
        return  byte2String(response.body().bytes());
    }*/
    /*
    * 异步的 get方法
    * 创建一个请求
    * 创建一个Call
    * 调用异步请求
    *
    * */
    public void getEngqueue(String url, final ICallBack callBack, final Class clazz){
        //添加一个请求
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        //实例化Call
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                        callBack.failed(e);
                        }
                    });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                final Object o = gson.fromJson(result, clazz);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.success(o);
                    }
                });
            }
        });
    }
   /*
        这个是 get同步请求需要用到的
        private static String byte2String(byte[] bytes){
        return new String(bytes);
    }*/
}




