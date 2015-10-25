package com.example.simon.gadgeothek.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class ReservationListViewHolder extends RecyclerView.ViewHolder {
    public View parent;
    public TextView textView;
    public Button button;


    public ReservationListViewHolder(View parent, TextView textView, Button button) {
        super(parent);
        this.parent = parent;
        this.textView = textView;
        this.button = button;
    }
}
