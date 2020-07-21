package com.example.myzc1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;


import com.example.myzc1.R;
import com.example.myzc1.base.BaseApp;
import com.example.myzc1.bean.DatasBean;
import com.example.myzc1.db.DatasBeanDao;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RcyAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<DatasBean> list;

    public RcyAdapter(Context context, ArrayList<DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rcy, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                ViewHolder holder1= (ViewHolder) holder;
        DatasBean bean = list.get(position);
       holder1.tv1.setText(bean.getChapterName());
       holder1.tv2.setText(bean.getNiceDate());
       holder1.tv3.setText(bean.getTitle());
       holder1.tv4.setText(bean.getAuthor());

        DatasBeanDao dao = BaseApp.getInstance().getDaoSession().getDatasBeanDao();

        if(bean.getFalg()){
            holder1.img.setImageResource(R.drawable.heart_select);

        }else{
            holder1.img.setImageResource(R.drawable.heart_unselect);
        }

        holder1.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean falg = bean.getFalg();
                bean.setFalg(!falg);
                if(bean.getFalg()){
                    holder1.img.setImageResource(R.drawable.heart_select);
                    dao.insertOrReplace(bean);
                }else{
                    holder1.img.setImageResource(R.drawable.heart_unselect);
                    dao.delete(bean);
                }
            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv1;
        public TextView tv2;
        public TextView tv3;
        public TextView tv4;
        public ImageView img;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv1 = (TextView) rootView.findViewById(R.id.tv1);
            this.tv2 = (TextView) rootView.findViewById(R.id.tv2);
            this.tv3 = (TextView) rootView.findViewById(R.id.tv3);
            this.tv4 = (TextView) rootView.findViewById(R.id.tv4);
            this.img = (ImageView) rootView.findViewById(R.id.img);
        }

    }
}
