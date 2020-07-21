package com.example.myzc1.model;

import com.example.myzc1.base.BaseApp;
import com.example.myzc1.base.BaseModel;
import com.example.myzc1.bean.DatasBean;
import com.example.myzc1.bean.JsonBean;
import com.example.myzc1.db.DatasBeanDao;
import com.example.myzc1.net.ApiService;
import com.example.myzc1.net.MainCallBack;
import com.example.myzc1.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModel extends BaseModel {

    private final DatasBeanDao beanDao;

    public MainModel(){
        beanDao =BaseApp.getInstance().getDaoSession().getDatasBeanDao();
    }
    public void getDatas(MainCallBack mainCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<JsonBean> obSer = apiService.getJson();
        obSer.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                         addDisposable(d);
                    }

                    @Override
                    public void onNext(JsonBean jsonBean) {
                        List<DatasBean> datas = jsonBean.getData().getDatas();
                        List<DatasBean> datasBeans = lodAll(datas);
                        mainCallBack.onSuccess(datasBeans);
                    }



                    @Override
                    public void onError(Throwable e) {
                           mainCallBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private List<DatasBean> lodAll(List<DatasBean> datas) {

        List<DatasBean> datasBeans = beanDao.loadAll();

        for (int i = 0; i < datas.size(); i++) {
            DatasBean bean = datas.get(i);
            for (int j = 0; j <datasBeans.size() ; j++) {
                DatasBean datasBean = datasBeans.get(j);
                if(bean.getTitle().equals(datasBean.getTitle())){
                       bean.setFalg(true);
                  }
            }

        }
        return  datas;
    }
}
