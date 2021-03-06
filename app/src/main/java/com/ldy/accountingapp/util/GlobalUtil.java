package com.ldy.accountingapp.util;

import android.content.Context;

import com.ldy.accountingapp.CategoryResBean;
import com.ldy.accountingapp.MainActivity;
import com.ldy.accountingapp.R;
import com.ldy.accountingapp.database.RecordDatabaseHelper;

import java.util.LinkedList;

/**
 * Created by ldy on 19/2/23.
 */

public class GlobalUtil {

    private static final String TAG = "GlobalUtil";

    private static GlobalUtil instance;

    public RecordDatabaseHelper mDatabaseHelper;

    private Context mContext;

    public MainActivity mMainActivity;

    public LinkedList<CategoryResBean> costRes = new LinkedList<>();
    public LinkedList<CategoryResBean> earnRes = new LinkedList<>();

    private static String[] costTitle = {"General", "Food", "Drinks","Groceries", "Shopping", "Personal","Entertain","Movies", "Social", "Transport",
            "App Store","Mobile","Computer","Gifts", "Housing", "Travel","Tickets","Books", "Medical","Transfer"};


    private static int [] costIconRes = {
            R.drawable.icon_general_white,
            R.drawable.icon_food_white,
            R.drawable.icon_drinking_white,
            R.drawable.icon_groceries_white,
            R.drawable.icon_shopping_white,
            R.drawable.icon_personal_white,
            R.drawable.icon_entertain_white,
            R.drawable.icon_movie_white,
            R.drawable.icon_social_white,
            R.drawable.icon_transport_white,
            R.drawable.icon_appstore_white,
            R.drawable.icon_mobile_white,
            R.drawable.icon_computer_white,
            R.drawable.icon_gift_white,
            R.drawable.icon_house_white,
            R.drawable.icon_travel_white,
            R.drawable.icon_ticket_white,
            R.drawable.icon_book_white,
            R.drawable.icon_medical_white,
            R.drawable.icon_transfer_white
    };
    private static int [] costIconResBlack = {
            R.drawable.icon_general,
            R.drawable.icon_food,
            R.drawable.icon_drinking,
            R.drawable.icon_groceries,
            R.drawable.icon_shopping,
            R.drawable.icon_presonal,
            R.drawable.icon_entertain,
            R.drawable.icon_movie,
            R.drawable.icon_social,
            R.drawable.icon_transport,
            R.drawable.icon_appstore,
            R.drawable.icon_mobile,
            R.drawable.icon_computer,
            R.drawable.icon_gift,
            R.drawable.icon_house,
            R.drawable.icon_travel,
            R.drawable.icon_ticket,
            R.drawable.icon_book,
            R.drawable.icon_medical,
            R.drawable.icon_transfer
    };

    private static String[] earnTitle = {"General", "Reimburse", "Salary","RedPocket","Part-time", "Bonus","Investment"};

    private static int[] earnIconRes = {
            R.drawable.icon_general_white,
            R.drawable.icon_reimburse_white,
            R.drawable.icon_salary_white,
            R.drawable.icon_redpocket_white,
            R.drawable.icon_parttime_white,
            R.drawable.icon_bonus_white,
            R.drawable.icon_investment_white};

    private static int[] earnIconResBlack = {
            R.drawable.icon_general,
            R.drawable.icon_reimburse,
            R.drawable.icon_salary,
            R.drawable.icon_redpocket,
            R.drawable.icon_parttime,
            R.drawable.icon_bonus,
            R.drawable.icon_investment};





    public static GlobalUtil getInstance(){
        if(instance == null){
            instance = new GlobalUtil();
        }

        return instance;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
        mDatabaseHelper = new RecordDatabaseHelper(mContext,RecordDatabaseHelper.DB_NAME,null,1);

        for (int i=0;i<costIconRes.length;i++){
            CategoryResBean bean = new CategoryResBean();
            bean.title = costTitle[i];
            bean.resWhite = costIconRes[i];
            bean.resBlack = costIconResBlack[i];

            costRes.add(bean);
        }

        for (int i=0;i<earnIconRes.length;i++){
            CategoryResBean bean = new CategoryResBean();
            bean.title = earnTitle[i];
            bean.resWhite = earnIconRes[i];
            bean.resBlack = earnIconResBlack[i];

            earnRes.add(bean);
        }

    }

    public int getResourceId(String category){
        for(CategoryResBean res : costRes){
            if(res.title.equals(category)){
                return res.resWhite;
            }
        }

        for(CategoryResBean res : earnRes){
            if(res.title.equals(category)){
                return res.resWhite;
            }
        }

        return costRes.get(0).resWhite;
    }


}
