package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.time.chrono.MinguoChronology;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    Cursor cursor;
    SQLiteDatabase db;
    DBhelper dBhelper;
    container adapter;
    RecyclerView recyclerView;
    Drawable drawable;
    Drawable wrappedDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawable = AppCompatResources.getDrawable(MainActivity.this,R.drawable.circle);
        wrappedDrawable = DrawableCompat.wrap(drawable);


        dBhelper = new DBhelper(MainActivity.this);
        db = dBhelper.getWritableDatabase();
        cursor = db.query("wait_table", null, null, null, null, null, null);

        adapter = new container(MainActivity.this, cursor);
        recyclerView = findViewById(R.id.Recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        long id = (long) viewHolder.itemView.getTag();
                        removeGuest(id);
                        adapter.swapCursor(getAllGuests());
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.swapCursor(getAllGuests());
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("Are you sure about that?");
                alertDialog.show();
            }
        }).attachToRecyclerView(recyclerView);
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
//                Toast.makeText(MainActivity.this, "item1 selected", Toast.LENGTH_SHORT).show();
                add();
                return true;
            case R.id.item2:
//                Toast.makeText(MainActivity.this, "item2 selected", Toast.LENGTH_SHORT).show();
                setting();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setting(){
        Intent intent = new Intent(MainActivity.this, Setting.class);
        startActivity(intent);
    }

    public void add() {
        Intent intent = new Intent(MainActivity.this, add.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                int size = data.getIntExtra("size", 0);
                ContentValues cv = new ContentValues();
                cv.put("Name", name);
                cv.put("number", size);
                db.insert("wait_table", null, cv);
                adapter.swapCursor(getAllGuests());
            }
        }
    }


    private Cursor getAllGuests() {
        return db.query(
                "wait_table",
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    private boolean removeGuest(long id) {
        // COMPLETED (2) Inside, call mDb.delete to pass in the TABLE_NAME and the condition that WaitlistEntry._ID equals id
        return db.delete("wait_table", "ID =" + id, null) > 0;
    }


    private void loadColorFromPreferences(SharedPreferences sharedPreferences) {

        String key = getString(R.string.pref_color_key);
        String value = getString(R.string.pref_color_red_value);
        System.out.println("key = "+key);
        System.out.println("value = "+value);
    }

    public void setcolor(String x , String y){
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_color_key))) {
            System.out.println("Changed!!!!!");
        }
    }
}
