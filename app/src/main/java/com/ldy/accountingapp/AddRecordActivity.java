package com.ldy.accountingapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ldy.accountingapp.util.GlobalUtil;

public class AddRecordActivity extends AppCompatActivity implements View.OnClickListener,CategoryRecyclerAdapter.OnCategoryClickListener{

    private static String TAG = "AddRecordActivity" ;

    private TextView amountTextview;

    private RecyclerView mCategoryRecyclerView;

    private EditText mEditText;

    private CategoryRecyclerAdapter mAdapter;

    private String userInput="";

    private String mCategory="General";

    private RecordBean.RecordType mType = RecordBean.RecordType.RECORD_TYPE_EXPENSE;

    private String mRemark = mCategory;

    private RecordBean record = new RecordBean();

    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        findViewById(R.id.keyboard_one).setOnClickListener(this);
        findViewById(R.id.keyboard_two).setOnClickListener(this);
        findViewById(R.id.keyboard_three).setOnClickListener(this);
        findViewById(R.id.keyboard_four).setOnClickListener(this);
        findViewById(R.id.keyboard_five).setOnClickListener(this);
        findViewById(R.id.keyboard_six).setOnClickListener(this);
        findViewById(R.id.keyboard_seven).setOnClickListener(this);
        findViewById(R.id.keyboard_eight).setOnClickListener(this);
        findViewById(R.id.keyboard_nine).setOnClickListener(this);
        findViewById(R.id.keyboard_zero).setOnClickListener(this);

        getSupportActionBar().setElevation(0);

        handleDot();
        handleTypeChange();
        handleBackspace();
        handleDone();


        amountTextview = (TextView) findViewById(R.id.textView_amount);
        mEditText = (EditText) findViewById(R.id.edit_text);

        mEditText.setText(mRemark);

        mCategoryRecyclerView = (RecyclerView) findViewById(R.id.category_recycler_view);

        mAdapter = new CategoryRecyclerAdapter(getApplicationContext());

        mCategoryRecyclerView.setAdapter(mAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),4);
        mCategoryRecyclerView.setLayoutManager(layoutManager);

        mAdapter.notifyDataSetChanged();

        mAdapter.setCategoryClickListener(this);

        RecordBean recvRecord = (RecordBean) getIntent().getSerializableExtra("record");

        if(recvRecord != null){
            isEdit = true;
            record = recvRecord;
        }


    }


    private void handleDot(){
        findViewById(R.id.keyboard_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!userInput.isEmpty() && !userInput.contains(".")){
                    userInput += ".";
                }
                Log.d(TAG,". click!!");
            }
        });
    }

    private void handleTypeChange(){
        findViewById(R.id.keyboard_type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mType == RecordBean.RecordType.RECORD_TYPE_EXPENSE){
                    mType = RecordBean.RecordType.RECORD_TYPE_INCOME;
                } else {
                    mType = RecordBean.RecordType.RECORD_TYPE_EXPENSE;
                }

                mAdapter.changeType(mType);
                mCategory = mAdapter.getUserSelect();
            }
        });
    }

    private void handleBackspace(){
        findViewById(R.id.keyboard_backspace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //11.21
                if(userInput.length()>0){
                    userInput = userInput.substring(0,userInput.length()-1);
                }

                //11.
                if(userInput.length()>0 && userInput.indexOf(".") == userInput.length()-1){
                    userInput = userInput.substring(0,userInput.length()-1);
                }

                updateAmount();


                Log.d(TAG,"backspace click!!");
            }
        });
    }

    private void handleDone(){
        findViewById(R.id.keyboard_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!userInput.isEmpty()){
                    double amount = Double.valueOf(userInput);
                    Log.d(TAG,"input amount is: "+amount);

                    record.setAmount(amount);
                    record.setCategory(mAdapter.getUserSelect());
                    record.setRemark(mEditText.getText().toString());
                    if(mType == RecordBean.RecordType.RECORD_TYPE_EXPENSE){
                        record.setType(1);
                    }else{
                        record.setType(2);
                    }

                    if(isEdit){
                        GlobalUtil.getInstance().mDatabaseHelper.updateRecord(record);

                    } else{
                        GlobalUtil.getInstance().mDatabaseHelper.addRecord(record);
                    }

                    finish();

                } else{
                    Toast.makeText(getApplicationContext(),"Input amount is zero!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onClick(View v) {

        Button button = (Button) v;
        String input = button.getText().toString();
        //11.  11.1  11.11

            if (userInput.length() == 2 || userInput.indexOf(".") != userInput.length() - 3) {
                userInput += input;
            }

        updateAmount();

//        Log.d(TAG,"User input is: "+userInput);

    }

    private void updateAmount(){

        //00 01 002 00.2
        if(userInput.length()>1 && userInput.charAt(0) == '0' && userInput.charAt(1) != '.'){
            userInput = userInput.substring(1,userInput.length());
        }
        if (userInput.contains(".")){
            if(userInput.indexOf(".")==userInput.length()-1){
                amountTextview.setText(userInput+"00");
            }
            else if(userInput.indexOf(".")==userInput.length()-2){
                amountTextview.setText(userInput+"0");
            }
            else if(userInput.indexOf(".")==userInput.length()-3){
                amountTextview.setText(userInput);
            }
        } else if (userInput.isEmpty()){
            amountTextview.setText("0.00");
        } else {
            amountTextview.setText(userInput+".00");
        }

    }

    @Override
    public void onClick(String categoryText) {
        mCategory = categoryText;
        mEditText.setText(categoryText);
    }
}
