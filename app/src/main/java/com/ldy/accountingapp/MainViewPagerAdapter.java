package com.ldy.accountingapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ldy.accountingapp.fragment.MainFragment;

import java.util.LinkedList;

/**
 * Created by ldy on 19/1/6.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    LinkedList<MainFragment> mMainFragments = new LinkedList<>();

    LinkedList<String> mDateList = new LinkedList<>();


    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragments();

    }

    private void initFragments(){
        mDateList.add("01-05");
        mDateList.add("01-06");
        mDateList.add("01-07");

        for(String date:mDateList){
            MainFragment fragment = new MainFragment(date);
            mMainFragments.add(fragment);
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
}
