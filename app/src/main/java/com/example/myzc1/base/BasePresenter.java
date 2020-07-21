package com.example.myzc1.base;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseView> {
    public V baseView;
    public ArrayList<BaseModel> models =new ArrayList<>();

    public BasePresenter(){
         initModel();
    }

    protected abstract void initModel();

    public void addModel(BaseModel baseModel){
         models.add(baseModel);
    }

    public void setView(V view){
        baseView=view;
    }

    public void disPose(){
        baseView=null;
        for (int i = 0; i < models.size(); i++) {
            BaseModel model = models.get(i);
            model.disPose();
        }
    }
}
