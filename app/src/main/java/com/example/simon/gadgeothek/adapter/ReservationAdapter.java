package com.example.simon.gadgeothek.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.simon.gadgeothek.R;
import com.example.simon.gadgeothek.domain.Gadget;

import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationViewHolder> {

    private List<Gadget> gadgets;

    public ReservationAdapter(List<Gadget> gadgets) {

        this.gadgets = gadgets;
    }

    @Override
    public ReservationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.reservation_rowlayout, parent, false);
        CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkBox);
        ReservationViewHolder viewHolder = new ReservationViewHolder(v, checkBox);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReservationViewHolder holder, int position) {
        final Gadget gadget = gadgets.get(position);
        holder.checkBox.setText(gadget.getName());
        holder.checkBox.setChecked(gadget.isSelected());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //set object's last status
                gadget.setSelected(isChecked);
            }


        });


    }

    @Override
    public int getItemCount() {
        return gadgets.size();
    }

    public Gadget getGadget(int position){

        return gadgets.get(position);
    }

    public List<Gadget> getGadgets(){
        return gadgets;
    }
}
