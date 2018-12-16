package com.example.yangweihan1210.IMode;

import com.example.yangweihan1210.Bean.UserBean;
import com.example.yangweihan1210.CallBack.MyCallBack;
import com.example.yangweihan1210.HttpUtil;

public class IModelleml implements IMode {
    @Override
    public void toRequsert1(String url, final MyCallBack callBack) {

       HttpUtil.getInstance().getRequsert(url, UserBean.class, new HttpUtil.Callbak<UserBean>() {
           @Override
           public void onSuccess(UserBean userBean) {
               callBack.setData(userBean);
           }
       });
    }

    @Override
    public void toRequsert2(String url, MyCallBack callBack) {

    }
}
