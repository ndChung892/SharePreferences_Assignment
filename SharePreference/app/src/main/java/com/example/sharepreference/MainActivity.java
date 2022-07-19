package com.example.sharepreference;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String MY_SHARE_PREFERENCES =  "MY_SHARE_PREFERENCES";

    private EditText edtName, edtAddress, edtEmail;
    private Button btnSave, btnDisplay;
    TextView txtDisplay;
    private Model model;
    private List<Model> lModel= new ArrayList<>();
    private SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        edtAddress= findViewById(R.id.edtAddress);
        edtEmail = findViewById(R.id.edtEmail);
        btnDisplay = findViewById(R.id.btnDisplayData);
        txtDisplay = findViewById(R.id.txtDisplay);
        btnSave = findViewById(R.id.btnSaveData);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtDisplay.setText(mSharedPreferences.getString("name",""));
            }
        });
        try {
            MasterKey masterKey =
                    new MasterKey.Builder(getApplication(),"Chung")
                            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                            .build();
            mSharedPreferences = EncryptedSharedPreferences.create(
                    getApplicationContext(),
                    MY_SHARE_PREFERENCES,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }



    }
    private void initData() {
        String name = edtName.getText().toString();
        String address= edtAddress.getText().toString();
        String email = edtEmail.getText().toString();
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("name", name);
        editor.putString("address", address);
        editor.putString("email", email);
        Model model = new Model(name, address, email);
        lModel.add(model);
        editor.apply();

    }
}