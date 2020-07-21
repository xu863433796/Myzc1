package com.example.myzc1;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myzc1.fragments.Fragment1;
import com.example.myzc1.fragments.Fragment2;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private Toolbar tb;
    private RelativeLayout rll;
    private TabLayout tbl;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        tb = (Toolbar) findViewById(R.id.tb);
        rll = (RelativeLayout) findViewById(R.id.rll);
        tbl = (TabLayout) findViewById(R.id.tbl);

        setSupportActionBar(tb);
        manager = getSupportFragmentManager();
        FragmentTransaction shiwu = manager.beginTransaction();
        Fragment1 fragment1 = new Fragment1();
        Fragment1 fragment2 = new Fragment1();
        Fragment1 fragment3 = new Fragment1();

        Fragment2 fragment21 = new Fragment2();
        //shiwu.add(R.id.rll,fragment21).commit();


        shiwu.add(R.id.rll,fragment1)
                .add(R.id.rll,fragment21)
                .add(R.id.rll,fragment2)
                .add(R.id.rll,fragment3)
                .hide(fragment21)
                .hide(fragment2)
                .hide(fragment3)
                .show(fragment1)
                .commit();

        tbl.addTab(tbl.newTab().setText("首页").setIcon(R.drawable.heart_select));
        tbl.addTab(tbl.newTab().setText("广场").setIcon(R.drawable.heart_select));
        tbl.addTab(tbl.newTab().setText("公众号").setIcon(R.drawable.heart_select));
        tbl.addTab(tbl.newTab().setText("体系").setIcon(R.drawable.heart_select));


        tbl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String s = tab.getText().toString();
                title.setText(s);

                switch (tab.getPosition()){
                    case 0:manager.beginTransaction().show(fragment1)
                            .hide(fragment2).hide(fragment3).hide(fragment21).commit();
                        break;
                    case 1:manager.beginTransaction().show(fragment21)
                            .hide(fragment2).hide(fragment3).hide(fragment1).commit();
                        break;
                    case 2:manager.beginTransaction().show(fragment2)
                            .hide(fragment1).hide(fragment3).hide(fragment21).commit();
                        break;
                    case 3:manager.beginTransaction().show(fragment3)
                            .hide(fragment2).hide(fragment1).hide(fragment21).commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });









    }
}
