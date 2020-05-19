package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

        okbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nametext.getText().length() == 0 ||
                        numbertext.getText().length() == 0) {
                    return;
                }
                //default party size to 1
                int partySize = 1;
                try {
                    //mNewPartyCountEditText inputType="number", so this should always work
                    partySize = Integer.parseInt(numbertext.getText().toString());
                } catch (NumberFormatException ex) {
                    Log.e("LOG_TAG", "Failed to parse party size text to number: " + ex.getMessage());
                }

                Intent resulti = new Intent();
                resulti.putExtra("size", partySize);
                resulti.putExtra("name", nametext.getText().toString());
                setResult(RESULT_OK, resulti);
                finish();
            }
        });

        cancelbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
