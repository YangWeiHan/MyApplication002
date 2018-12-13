package com.example.okhttp.Presenter;

import com.example.okhttp.IModel.IModellmpl;
import com.example.okhttp.IVew.IVew;
import com.example.okhttp.MyCallBack.MyCallBack;

import java.util.Map;

public class IPresenterlmlp implements IPresenter {
    private IVew iVew;
    private IModellmpl iModellmpl;

    public IPresenterlmlp(IVew iVew) {
        this.iVew = iVew;
        iModellmpl = new IModellmpl();
    }

    public void onDetach(){
        if (iModellmpl != null){
            iModellmpl = null;
        }
        if (iVew != null){
            iVew = null;
        }
    }

    @Override
    public void startRequest(String url, Class clazz) {
        iModellmpl.requestData(url, clazz, new MyCallBack() {
            @Override
            public void setData(Object data) {
                iVew.showResponseData(data);
            }

            @Override
            public void setFail(String msg) {
                iVew.fail(msg);
            }
        });
    }


}
