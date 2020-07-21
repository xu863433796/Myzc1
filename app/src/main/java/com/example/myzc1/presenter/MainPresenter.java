package com.example.myzc1.presenter;

import com.example.myzc1.base.BaseApp;
import com.example.myzc1.base.BasePresenter;
import com.example.myzc1.bean.DatasBean;
import com.example.myzc1.db.DatasBeanDao;
import com.example.myzc1.model.MainModel;
import com.example.myzc1.net.MainCallBack;
import com.example.myzc1.view.MainView;

import java.util.List;

public class MainPresenter extends BasePresenter<MainView> implements MainCallBack {

    private MainModel mainModel;

    @Override
    protected void initModel() {
        mainModel = new MainModel();
        addModel(mainModel);
    }

    @Override
    public void onSuccess(List<DatasBean> datasBeans) {
        baseView.getDatas(datasBeans);
    }

    @Override
    public void onFail(String string) {
          baseView.showToast(string);
    }

    public void getDatas() {
        mainModel.getDatas(this);


    }
}
