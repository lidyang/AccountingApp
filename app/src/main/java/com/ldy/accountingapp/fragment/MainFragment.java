package com.ldy.accountingapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ldy.accountingapp.R;

/**
 * Created by ldy on 19/1/6.
 */

public class MainFragment extends Fragment {

    private TextView mDayTextView;
    private ListView mListView;

    private String mDate="";


    @SuppressLint("ValidFragment")
    public MainFragment(String date){
        this.mDate = date;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main,container,false);
        mDayTextView = v.findViewById(R.id.day_text);
        mListView = v.findViewById(R.id.list_view);
        mDayTextView.setText(mDate);



        return v;
    }
}
