package com.example.sharepreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Display;

public class MySharePreferences {
    private static final String MY_SHARE_PREFERENCES =  "MY_SHARE_PRIFERENCES";
    private Context mContext;// biến môi trường
    public MySharePreferences(Context mContext){
        this.mContext = mContext;
    }
    public void putValue(String key, String values){
        SharedPreferences sharedPreferences
                = mContext
                .getSharedPreferences(MY_SHARE_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(key, values);
        editor.apply();
    }
    public String getValue(String key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARE_PREFERENCES,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }


}
