package com.example.yangweihan1213.Presenter;

import com.example.yangweihan1213.CallBack.MyCallBack;
import com.example.yangweihan1213.Model.IModellmpl;
import com.example.yangweihan1213.View.IVIew;

public class IPresenterlmpl implements IPresenter {
    //P层   实例化  View层和Model层
    private IVIew ivIew;
    private IModellmpl iModellmpl;

    public IPresenterlmpl(IVIew ivIew) {
        this.ivIew = ivIew;
        iModellmpl = new IModellmpl();
    }

    public void onDetach(){
        if (iModellmpl != null){
            iModellmpl = null;
        }
        if (ivIew != null){
            ivIew = null;
        }
    }

    @Override
    public void startRequest(String url, Class clazz) {
        iModellmpl.requesrData(url, clazz, new MyCallBack() {
            @Override
            public void setData(Object data) {
                ivIew.setRequestData(data);
            }
            @Override
            public void setFail(String msg) {
            }
        });
    }

    @Override
    public void startRequestXiangqing(String url, Class clazz) {
        iModellmpl.requesrDataXianqing(url, clazz, new MyCallBack() {
            @Override
            public void setData(Object data) {
                ivIew.setRequestData(data);
            }

            @Override
            public void setFail(String msg) {

            }
        });

    }
}
