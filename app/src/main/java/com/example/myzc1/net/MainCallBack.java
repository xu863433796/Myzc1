package com.example.myzc1.net;

import com.example.myzc1.bean.DatasBean;

import java.util.List;

public interface MainCallBack {
    void onSuccess(List<DatasBean> datasBeans);
    void onFail(String string);
}
