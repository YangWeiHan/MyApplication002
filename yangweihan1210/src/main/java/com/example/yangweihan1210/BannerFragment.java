package com.example.yangweihan1210;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yangweihan1210.Bean.BannerBean;
import com.example.yangweihan1210.IPresenter.IPresenterleml;
import com.example.yangweihan1210.View.IView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class BannerFragment extends Fragment implements IView {
    private String path = "http://api.tianapi.com/wxnew/?key=c4aa776e0a51d57d6750511e2baa46b6&num=6&page=1";
    private IPresenterleml iPresenterleml;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.bannerfragment, null);



        iPresenterleml = new IPresenterleml(this);


        view.findViewById(R.id.qrcode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA},100);
                    startActivity(new Intent(getActivity(),QRCode1Activity.class));
                }else {
                    startActivity(new Intent(getActivity(),QRCode1Activity.class));
                }

            }
        });
        return view;
    }



    @Override
    public void showResponesData(Object data) {

    }

    @Override
    public void success(String msg) {

    }

    @Override
    public void fail(String msg) {

    }
}

