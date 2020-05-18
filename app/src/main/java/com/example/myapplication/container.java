package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class container extends RecyclerView.Adapter<container.viewholder> {

    Context mcontext;
    Cursor mcursor;

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

    @Override
    public void onBindViewHolder(@NonNull container.viewholder holder, int position) {
        if (!mcursor.moveToPosition(position))
            return;

        String disname = mcursor.getString(mcursor.getColumnIndex("number"));
        String dissize = mcursor.getString(mcursor.getColumnIndex("Name"));

        holder.who.setText(disname);
        holder.size.setText(String.valueOf(dissize));

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
}
