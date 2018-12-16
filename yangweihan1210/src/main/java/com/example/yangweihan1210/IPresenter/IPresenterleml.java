package com.example.yangweihan1210.IPresenter;

import com.example.yangweihan1210.CallBack.MyCallBack;
import com.example.yangweihan1210.IMode.IModelleml;
import com.example.yangweihan1210.View.IView;

public class IPresenterleml implements IPresnter {

    private IModelleml iModelleml;
    private IView iView;


    public IPresenterleml(IView iView) {
        this.iView = iView;
        iModelleml = new IModelleml();
    }



    @Override
    public void startResqusert1(String url) {
        iModelleml.toRequsert1(url, new MyCallBack() {
            @Override
            public void setData(Object data) {
                iView.showResponesData(data);

            }
        });
    }

    @Override
    public void startResqusert2(String url) {

    }
    //解绑
    public void onDatech(){
        if (iModelleml != null){
            iModelleml = null;
        }
        if (iView != null){
            iView = null;
        }
    }
}
