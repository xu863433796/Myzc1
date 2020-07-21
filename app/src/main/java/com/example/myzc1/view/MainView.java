package com.example.myzc1.view;

import com.example.myzc1.base.BaseView;
import com.example.myzc1.bean.DatasBean;

import java.util.List;

public interface MainView extends BaseView {
    void getDatas(List<DatasBean> datasBeans);

}
