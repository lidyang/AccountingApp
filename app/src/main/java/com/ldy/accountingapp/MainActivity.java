package com.ldy.accountingapp;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ldy.accountingapp.util.DateUtil;
import com.ldy.accountingapp.util.GlobalUtil;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private static final String TAG = "MainActivity";

    private TickerView mTickerView;
    private ViewPager mViewPager;
    private MainViewPagerAdapter mAdapter;
    private TickerView mAmountTextTicker;
    private TextView mDateText;
    private int currentPagerPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);
        GlobalUtil.getInstance().mMainActivity = this;
        GlobalUtil.getInstance().setContext(getApplicationContext());

        mAmountTextTicker = (TickerView) findViewById(R.id.amount_text);
        mAmountTextTicker.setCharacterLists(TickerUtils.provideNumberList());

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mDateText = (TextView) findViewById(R.id.date_text);
        mAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mAdapter.notifyDataSetChanged();
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setCurrentItem(mAdapter.getLastIndex());


        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddRecordActivity.class);
                startActivityForResult(intent,1);
            }
        });




    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,"Context is: "+ getApplicationContext().toString());
        mAdapter.reload();
        uadateHeader();

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentPagerPosition = position;
        uadateHeader();

    }

    public void uadateHeader(){
        String amount = String.valueOf(mAdapter.getTotalCost(currentPagerPosition)+" ");
        mAmountTextTicker.setText(amount);
        String dateStr = mAdapter.getDateStr(currentPagerPosition);
        mDateText.setText(DateUtil.getWeekDay(dateStr));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

