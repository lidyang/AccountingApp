package com.ldy.accountingapp;

import android.util.Log;

import com.ldy.accountingapp.util.DateUtil;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by ldy on 19/1/5.
 */

public class RecordBean implements Serializable {

    private static String TAG = "RecordBean";


    public enum RecordType {
        RECORD_TYPE_EXPENSE, RECORD_TYPE_INCOME
    }

    private String mUuid;
    private double mAmount;

    //1消费 2收入
    private RecordType mType;
    private String mCategory;
    private String mRemark;
    private long mTimeStamp;
    private String mDate;//2019-01-05

    public RecordBean() {
        mUuid = UUID.randomUUID().toString();
        mTimeStamp = System.currentTimeMillis();
        mDate = DateUtil.getFormattedDate();
//        Log.d(TAG, mDate + " " + DateUtil.getFormattedTime(mTimeStamp));
    }

    public String getUuid() {
        return mUuid;
    }

    public void setUuid(String uuid) {
        mUuid = uuid;
    }

    public double getAmount() {
        return mAmount;
    }

    public void setAmount(double amount) {
        mAmount = amount;
    }

    public int getType() {

        if(mType == RecordType.RECORD_TYPE_EXPENSE){
            return 1;
        } else {
            return  2;
        }
    }

    public void setType(int type) {
        if (type == 1) {
            mType = RecordType.RECORD_TYPE_EXPENSE;
        } else {
            mType = RecordType.RECORD_TYPE_INCOME;
        }
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public String getRemark() {
        return mRemark;
    }

    public void setRemark(String remark) {
        mRemark = remark;
    }

    public long getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        mTimeStamp = timeStamp;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }
}
