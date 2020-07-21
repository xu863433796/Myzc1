package com.example.myzc1.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseModel {
    private CompositeDisposable compositeDisposable;

    public void addDisposable(Disposable disposable){
        if(compositeDisposable==null){
            synchronized (this){
                if (compositeDisposable==null){
                    compositeDisposable=new CompositeDisposable();
                }
            }
        }
        compositeDisposable.add(disposable);
    }

    public void removeDisposable(Disposable disposable){
          if(compositeDisposable!=null){
               compositeDisposable.remove(disposable);
          }
    }

    public void disPose(){
        if(compositeDisposable!=null){
            compositeDisposable.dispose();
        }
    }
}
