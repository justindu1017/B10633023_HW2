package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class container extends RecyclerView.Adapter<container.viewholder> {

    Context mcontext;


    @NonNull
    @Override
    public container.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull container.viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class viewholder extends RecyclerView.ViewHolder {
        public viewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}