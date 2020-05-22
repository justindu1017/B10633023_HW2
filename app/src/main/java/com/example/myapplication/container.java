package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

public class container extends RecyclerView.Adapter<container.viewholder> implements SharedPreferences.OnSharedPreferenceChangeListener {

    Context mcontext;
    Cursor mcursor;
    int colorrr;

    public container(Context mcontext, Cursor mcursor) {
        this.mcontext = mcontext;
        this.mcursor = mcursor;
    }


    @NonNull
    @Override
    public container.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.objectt, parent, false);
        viewholder viewholder = new viewholder(view);
        return viewholder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull container.viewholder holder, int position) {
        if (!mcursor.moveToPosition(position))
            return;

        String disname = mcursor.getString(mcursor.getColumnIndex("number"));
        String dissize = mcursor.getString(mcursor.getColumnIndex("Name"));
        long id = mcursor.getLong(mcursor.getColumnIndex("ID"));
        holder.who.setText(disname);
        holder.size.setText(String.valueOf(dissize));
        holder.itemView.setTag(id);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mcontext);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        colorrr = getnowcolor(sharedPreferences);
        holder.size.setBackgroundColor(colorrr);
    }

    @Override
    public int getItemCount() {
        return mcursor.getCount();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView who, size;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            who = itemView.findViewById(R.id.who);
            size = itemView.findViewById(R.id.size);
        }
    }

    public void swapCursor(Cursor newCursor) {
        // Always close the previous mCursor first
        if (mcursor != null) mcursor.close();
        mcursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("color")) {
            getnowcolor(sharedPreferences);
        }
    }

    public  int getnowcolor(SharedPreferences sharedPreferences){
        System.out.println("getttt" + sharedPreferences.getString("color", "nuddddll"));
        int color;
        if (sharedPreferences.getString("color", "null").equals("0xFF0000FF")) {
            System.out.println("OKKKK");
            colorrr = Color.BLUE;
            color = colorrr;
        } else if (sharedPreferences.getString("color", "null").equals("0xFFFF0000")) {
            System.out.println("OKKKK");
            colorrr = Color.RED;
            color = colorrr;
        } else {
            colorrr = Color.GREEN;
            color = colorrr;
        }
        return color;
    }
}
