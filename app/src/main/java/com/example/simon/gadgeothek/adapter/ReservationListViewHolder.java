package com.example.simon.gadgeothek.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class ReservationListViewHolder extends RecyclerView.ViewHolder {
    public View parent;
    public TextView textView;


    public ReservationListViewHolder(View parent, TextView textView) {
        super(parent);
        this.parent = parent;
        this.textView = textView;
    }
}
