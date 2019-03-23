package com.ldy.accountingapp.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ldy.accountingapp.AddRecordActivity;
import com.ldy.accountingapp.ListViewAdapter;
import com.ldy.accountingapp.R;
import com.ldy.accountingapp.RecordBean;
import com.ldy.accountingapp.util.DateUtil;
import com.ldy.accountingapp.util.GlobalUtil;

import java.util.LinkedList;

/**
 * Created by ldy on 19/1/6.
 */

public class MainFragment extends Fragment implements AdapterView.OnItemLongClickListener {

    private static final String TAG = "MainFragment";

    private TextView mDayTextView;
    private ListView mListView;

    private ListViewAdapter mListAdapter;

    private LinkedList<RecordBean> mRecordList = new LinkedList<>();


    private String mDate = "";

    private View rootView;
    private Activity mActivity;

    @SuppressLint("ValidFragment")
    public MainFragment(String date) {
        mDate = date;
        mRecordList = GlobalUtil.getInstance().mDatabaseHelper.queryRecord(date);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        initView();

        mListView.setOnItemLongClickListener(this);

        return rootView;
    }

    private void initView() {

        mDayTextView = (TextView) rootView.findViewById(R.id.day_text);
        mListView = (ListView) rootView.findViewById(R.id.list_view);
        mDayTextView.setText(mDate);

        mListAdapter = new ListViewAdapter(getContext());
        Log.d(TAG, "Activity is: " + getActivity().toString() + "   Context: " + getContext().toString() + "   " + this.toString());
        mListAdapter.setData(mRecordList);
        mListView.setAdapter(mListAdapter);

        if (mListAdapter.getCount() > 0) {
            rootView.findViewById(R.id.no_record_lay).setVisibility(View.INVISIBLE);

        }

        mDayTextView.setText(DateUtil.getDateTitle(mDate));


    }

    public void reload() {

        mRecordList = GlobalUtil.getInstance().mDatabaseHelper.queryRecord(mDate);

        Log.d(TAG, "amount is: " + getTotalCost());
        Log.d(TAG, "activity: " + getActivity().toString());

        if (mListAdapter == null) {
            mListAdapter = new ListViewAdapter(getActivity().getApplicationContext());
        }
        mListAdapter.setData(mRecordList);
        mListView.setAdapter(mListAdapter);
        if (mListAdapter.getCount() > 0) {
            rootView.findViewById(R.id.no_record_lay).setVisibility(View.INVISIBLE);

        }

    }

    public int getTotalCost() {
        double amount = 0;
        for (RecordBean bean : mRecordList) {
            if (bean.getType() == 1){
                amount -= bean.getAmount();
            }
            else{
                amount += bean.getAmount();
            }

        }

        return (int) amount;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        showDiaog(position);
        return false;
    }

    private void showDiaog(final int index) {

        final String[] options = {"Remove", "Edit"};

        final RecordBean selectBean = mRecordList.get(index);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.create();

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //0-remove  1-edit
                if (which == 0) {
                    String uuid = selectBean.getUuid();
                    GlobalUtil.getInstance().mDatabaseHelper.removeRecord(uuid);
                    reload();
                    GlobalUtil.getInstance().mMainActivity.uadateHeader();

                } else if (which == 1) {

                    Intent intent = new Intent(getActivity(), AddRecordActivity.class);
                    Bundle extra = new Bundle();
                    extra.putSerializable("record", selectBean);
                    intent.putExtras(extra);
                    startActivityForResult(intent, 1);

                }
            }
        });

        builder.setNegativeButton("Cancel", null);

        builder.create().show();

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "Starting...");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause...");
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "Stoping...");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView...");
    }




    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;//保存activity引用

        Log.d(TAG, "activity: " + mActivity.toString());


    }

}
