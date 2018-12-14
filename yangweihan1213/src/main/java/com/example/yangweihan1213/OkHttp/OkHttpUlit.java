package com.example.yangweihan1213.OkHttp;

 ;
import android.os.Handler;
import android.os.Looper;


import com.example.yangweihan1213.CallBack.ICallBack;
 import com.google.gson.Gson;

 import java.io.IOException;
 import java.util.concurrent.TimeUnit;

 import okhttp3.Call;
 import okhttp3.Callback;
 import okhttp3.OkHttpClient;
 import okhttp3.Request;
 import okhttp3.Response;
 import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUlit {
    //单例
    private static  OkHttpUlit mInstance;
    //私有化
    private OkHttpClient mClient;
    //实例化Handler
    private Handler mHandler = new Handler(Looper.getMainLooper());
    /*
    * 第一步， 写一个单例   用一个 懒汉试
    * */
    public static OkHttpUlit getmInstance(){
        if (mInstance == null){
            synchronized (OkHttpUlit.class){
                if (null == mInstance){
                    mInstance = new OkHttpUlit();
                }
            }
        }
        return mInstance;
    }

    /**
     * 网络同步方法
     * 创建一个get请求
     * 设置一个路径
     * <p>
     * 通过mClient.newCall建一个Call对象
     * 调用同步请求方法
     * 完成构造方法，OkHttpClient
     */
    private OkHttpUlit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        /**
         * 使用构造者模式
         * 设置连接超时
         * 读取超时
         * 写超时
         * 添加拦截器
         */
        mClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }
    public void getEnqueue(String url, final Class clazz, final ICallBack iCallBack) {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = mClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iCallBack.setFail(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //网络请求连接成功
                String result = response.body().string();
                //解析
                Gson gson = new Gson();
                final Object o = gson.fromJson(result, clazz);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iCallBack.setData(o);
                    }
                });
            }
        });
    }
}
