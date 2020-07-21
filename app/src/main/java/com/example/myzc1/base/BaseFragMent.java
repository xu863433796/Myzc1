package com.example.myzc1.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragMent<P extends BasePresenter> extends Fragment implements BaseView {
    public P basepresenter;
    private Unbinder bind;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(getLayout(),null);
        bind = ButterKnife.bind(this, view);
        initPresenter();
        if(basepresenter!=null){
            basepresenter.setView(this);
        }

        initView();
        initData();
        initListener();

        return view;
    }

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initPresenter();

    protected abstract int getLayout();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
         bind.unbind();
        if(basepresenter!=null){
            basepresenter.disPose();
            basepresenter=null;

        }
    }
}
