package com.ldy.accountingapp;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

public class MainActivity extends AppCompatActivity {

    private TickerView mTickerView;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        adapter.notifyDataSetChanged();
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(adapter.getLastIndex());


        RecordBean recordBean = new RecordBean();



    }
}

