package com.example.yangweihan1210;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {

    private Gson gson;

    //单例
    private static HttpUtil instance;

    //创建私有构造
    private HttpUtil(){
        gson = new Gson();
    }
    //提供静态方法
    public static HttpUtil getInstance(){
        if (instance == null){
            instance = new HttpUtil();
        }
        return instance;
    }
    //定义一个接口
    public interface Callbak<T>{
        void onSuccess(T t);
    }
    //提供一个接口
    public void  getRequsert(final String urlStr,final Class clazz,final Callbak callbak){
        new AsyncTask<String,Void,Object>(){

            @Override
            protected Object doInBackground(String... strings) {
                return getRequsert(urlStr,clazz);
            }

            @Override
            protected void onPostExecute(Object a) {
                callbak.onSuccess(a);
            }
        }.execute(urlStr);
    }

    //创建GSon解析的方法
    public <T> T getRequsert(String urlStr,Class clazz){
        return (T) gson.fromJson(getRequsert(urlStr),clazz);
    }

    //创建网络请求的方法
    public String getRequsert(String urlStr){
        String result = "";
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            urlConnection.setRequestMethod("GET");
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200){
                result = stream2String(urlConnection.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    private  String stream2String(InputStream inputStream) throws IOException {
        InputStreamReader is  =new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(is);
        StringBuilder sb = new StringBuilder();
        for (String tmp = br.readLine(); tmp != null; tmp = br.readLine()){
            sb.append(tmp);
        }
        return sb.toString();
    }
}
