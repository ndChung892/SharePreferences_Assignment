package com.example.sharepreferencereceive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static final String TAG ="MainActivity";
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtDisplay = findViewById(R.id.txtGetData);
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.example.sharepreference.contentprovider/"),
                null,
                null,
                null,
                null);
        if(cursor!= null){
            cursor.moveToPosition(-1);
            int index = cursor.getColumnIndex("name");
            while(cursor.moveToNext()){
                String name = cursor.getString(index);
                String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                Log.d(TAG, "onCreate: "+name+ address+ email);
                txtDisplay.setText(name);
            }
        }




    }
}