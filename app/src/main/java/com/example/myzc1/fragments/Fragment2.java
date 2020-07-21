package com.example.myzc1.fragments;

import android.widget.Toast;

import com.example.myzc1.R;
import com.example.myzc1.adapter.RcyAdapter;
import com.example.myzc1.base.BaseFragMent;
import com.example.myzc1.bean.DatasBean;
import com.example.myzc1.presenter.MainPresenter;
import com.example.myzc1.view.MainView;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends BaseFragMent<MainPresenter> implements MainView {


    @BindView(R.id.rcy)
    RecyclerView rcy;

    private ArrayList<DatasBean> list;
    private RcyAdapter adapter;

    public Fragment2() {
        // Required empty public constructor
    }




    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        basepresenter.getDatas();
    }

    @Override
    protected void initView() {
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        adapter = new RcyAdapter(getContext(), list);
        rcy.setAdapter(adapter);
    }

    @Override
    protected void initPresenter() {
        basepresenter = new MainPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_2;
    }

    @Override
    public void getDatas(List<DatasBean> datasBeans) {
        if (datasBeans.size() > 0) {
            list.clear();
            list.addAll(datasBeans);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(getContext(), string, Toast.LENGTH_SHORT).show();
    }
}
