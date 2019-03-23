package com.ldy.accountingapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.ldy.accountingapp.database.RecordDatabaseHelper;
import com.ldy.accountingapp.fragment.MainFragment;
import com.ldy.accountingapp.util.DateUtil;
import com.ldy.accountingapp.util.GlobalUtil;

import java.util.LinkedList;

/**
 * Created by ldy on 19/1/6.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "MainViewPagerAdapter";

    LinkedList<MainFragment> mMainFragments = new LinkedList<>();


    LinkedList<String> mDateList = new LinkedList<>();



    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragments();

    }

    private void initFragments(){

        mDateList = GlobalUtil.getInstance().mDatabaseHelper.getAvaiableDate();

        if(!mDateList.contains(DateUtil.getFormattedDate())){
            mDateList.addLast(DateUtil.getFormattedDate());
        }

        for(String date:mDateList){
            MainFragment fragment = new MainFragment(date);
            mMainFragments.add(fragment);
        }

    }

    public void reload(){

        for(MainFragment fragment:mMainFragments){
//            Log.d(TAG,"Activity is: "+ .toString());

            Log.d(TAG,"amount: "+fragment.getTotalCost());


            fragment.reload();
        }

    }

    @Override
    public Fragment getItem(int position) {
        return mMainFragments.get(position);
    }

    @Override
    public int getCount() {
        return mMainFragments.size();
    }

    public int getLastIndex(){
        return mMainFragments.size()-1;
    }

    public String getDateStr(int index){
        return mDateList.get(index);
    }

    public int getTotalCost(int index){
        return mMainFragments.get(index).getTotalCost();
    }
}
