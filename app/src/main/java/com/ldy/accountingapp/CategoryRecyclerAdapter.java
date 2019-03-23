package com.ldy.accountingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ldy.accountingapp.util.GlobalUtil;

import java.util.LinkedList;

/**
 * Created by ldy on 19/2/23.
 */

public class CategoryRecyclerAdapter extends RecyclerView.Adapter <CategoryViewHolder> {

    private LayoutInflater mInflater;
    public Context mContext;

    private LinkedList<CategoryResBean> mCategoryResBeanLinkedList = GlobalUtil.getInstance().costRes;
    private String userSelect = "";

    private OnCategoryClickListener mCategoryClickListener;


    public CategoryRecyclerAdapter(Context context){
        mContext = context;
        mInflater = LayoutInflater.from(mContext);

        userSelect = mCategoryResBeanLinkedList.get(0).title;

    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_category,parent,false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);

        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        final CategoryResBean resBean = mCategoryResBeanLinkedList.get(position);

        holder.image.setImageResource(resBean.resBlack);
        holder.text.setText(resBean.title);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSelect=resBean.title;
                notifyDataSetChanged();

                if(mCategoryClickListener != null){
                    mCategoryClickListener.onClick(resBean.title);
                }
            }
        });

        if(holder.text.getText().toString().equals(userSelect)){
            holder.backgroud.setBackgroundResource(R.drawable.bg_edit_text);
        } else {
            holder.backgroud.setBackgroundResource(R.color.colorPrimary);
        }

    }

    public void changeType(RecordBean.RecordType type){
        if(type == RecordBean.RecordType.RECORD_TYPE_EXPENSE){
            mCategoryResBeanLinkedList = GlobalUtil.getInstance().costRes;
        } else {
            mCategoryResBeanLinkedList = GlobalUtil.getInstance().earnRes;
        }
        userSelect = mCategoryResBeanLinkedList.get(0).title;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCategoryResBeanLinkedList.size();
    }

    public interface OnCategoryClickListener{
        void onClick(String categoryText);
    }

    public String getUserSelect() {
        return userSelect;
    }

    public void setCategoryClickListener(OnCategoryClickListener categoryClickListener) {
        mCategoryClickListener = categoryClickListener;
    }
}

class CategoryViewHolder extends RecyclerView.ViewHolder{

    RelativeLayout backgroud;
    ImageView image;
    TextView text;


    public CategoryViewHolder(View itemView) {
        super(itemView);
        backgroud = itemView.findViewById(R.id.list_background);
        image = itemView.findViewById(R.id.image_view_category);
        text = itemView.findViewById(R.id.text_view_category);
    }
}