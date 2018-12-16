package com.example.yangweihan1210.View;

public interface IView<T> {

    void showResponesData(T data);

    void success(String msg);

    void fail(String msg);
}
