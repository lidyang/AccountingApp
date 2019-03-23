package com.ldy.accountingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ldy.accountingapp.util.GlobalUtil;

import java.util.LinkedList;

/**
 * Created by ldy on 19/1/7.
 */

public class ListViewAdapter extends BaseAdapter {

    private LinkedList<RecordBean> mRecordList;

    private LayoutInflater mInflater;
    private Context mContext;

    public ListViewAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(LinkedList<RecordBean> recordList){
        mRecordList = recordList;
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return mRecordList.size();
    }

    @Override
    public Object getItem(int position) {
        return mRecordList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_item,null);

            RecordBean bean = mRecordList.get(position);
            holder = new ViewHolder(convertView,bean);
            convertView.setTag(holder);

        } else{
            holder = (ViewHolder)convertView.getTag();
        }

        return convertView;
    }
}

class ViewHolder{

    private ImageView categoryImage;
    private TextView remarkText;
    private TextView timeText;
    private TextView amounttext;

    public ViewHolder(View itemView,RecordBean recordBean){

        categoryImage = itemView.findViewById(R.id.category_image);
        remarkText = itemView.findViewById(R.id.remark_text);
        timeText = itemView.findViewById(R.id.time_text);
        amounttext = itemView.findViewById(R.id.amount_text);

        categoryImage.setImageResource(GlobalUtil.getInstance().getResourceId(recordBean.getCategory()));

        remarkText.setText(recordBean.getRemark());
        timeText.setText(recordBean.getDate());
        if(recordBean.getType() == 1){
            amounttext.setText("-" + String.valueOf(recordBean.getAmount()));
        } else{
            amounttext.setText("+" + recordBean.getAmount());
        }



    }

}

