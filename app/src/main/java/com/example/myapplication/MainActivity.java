package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.time.chrono.MinguoChronology;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBhelper dBhelper = new DBhelper(MainActivity.this);
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        Cursor cursor = db.query("wait_table", null, null, null,null,null, null);

        container adapter  =new container(MainActivity.this, cursor);
        RecyclerView recyclerView = findViewById(R.id.Recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(MainActivity.this, "item1 selected", Toast.LENGTH_SHORT).show();
                add();
                return true;
            case R.id.item2:
                Toast.makeText(MainActivity.this, "item2 selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void add(){
        Intent intent = new Intent(MainActivity.this, add.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            String name = getIntent().getStringExtra("name");
            int size = getIntent().getIntExtra("size",0);
            System.out.println("name = "+ name + ", size = "+size);
        }
    }
}
