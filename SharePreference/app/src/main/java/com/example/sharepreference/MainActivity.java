package com.example.sharepreference;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText edtName, edtAddress, edtEmail;
    private Button btnSave, btnDisplay;
    TextView txtDisplay;
    private Model model;
    private List<Model> lModel= new ArrayList<>();
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
                txtDisplay.setText(DataLocalManager.getListData().toString());
            }
        });


    }
    private void initData() {
        String name = edtName.getText().toString();
        String address= edtAddress.getText().toString();
        String email = edtEmail.getText().toString();
        model = new Model(name, address, email);
        lModel.add(model);
        DataLocalManager.setListData(lModel);
    }
}