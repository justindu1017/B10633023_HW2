package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class add extends AppCompatActivity {
    Button okbut, cancelbut;
    EditText nametext,numbertext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        okbut = findViewById(R.id.ok);
        cancelbut = findViewById(R.id.cancel);
        nametext = findViewById(R.id.nametext);
        numbertext = findViewById(R.id.numbertext);



    }
}
