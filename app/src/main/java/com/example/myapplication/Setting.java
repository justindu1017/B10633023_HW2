package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting2);

        if(findViewById(R.id.forFreg)!=null){
            if(savedInstanceState!=null){
                return;
            }
            getFragmentManager().beginTransaction().add(R.id.forFreg, new SettingFragment()).commit();
        }
    }
}
