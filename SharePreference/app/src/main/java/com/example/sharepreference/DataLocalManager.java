package com.example.sharepreference;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataLocalManager {
    private static final String PUT_DATA_REFS = "PUT_DATA_REFS";
    private static DataLocalManager instance;
    private MySharePreferences mySharePreferences;

    public static void init(Context mContext){
        instance = new DataLocalManager();
        instance.mySharePreferences = new MySharePreferences(mContext);
    }
    public static DataLocalManager getInstance(){
        if(instance ==null){
            instance = new DataLocalManager();
        }
        return instance;
    }
    public static void setListData(List<Model> lModel){
        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(lModel).getAsJsonArray();
        String strJsonArray = jsonArray.toString();
        DataLocalManager.getInstance().mySharePreferences.putValue(PUT_DATA_REFS, strJsonArray);
    }
    public static List<Model> getListData(){
        Gson gson = new Gson();
        String strJsonArray = DataLocalManager.getInstance().mySharePreferences.getValue(PUT_DATA_REFS);
        List<Model> lModel = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(strJsonArray);
            JSONObject jsonObject;
            Model model;
            for(int i=0; i<jsonArray.length();i++ ){
                jsonObject = jsonArray.getJSONObject(i);
                model =gson.fromJson(jsonObject.toString(), Model.class);
                lModel.add(model);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lModel;
    }
}