package com.example.simon.gadgeothek.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.simon.gadgeothek.R;
import com.example.simon.gadgeothek.domain.Reservation;

import java.util.List;

public class ReservationListAdapter extends RecyclerView.Adapter<ReservationListViewHolder> {

    private List<Reservation> reservations;

    public ReservationListAdapter(List<Reservation> reservations) {

        this.reservations = reservations;
    }

    @Override
    public ReservationListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.reservationlist_rowlayout, parent, false);
        TextView textView = (TextView) v.findViewById(R.id.reservationlist);
        ReservationListViewHolder viewHolder = new ReservationListViewHolder(v, textView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReservationListViewHolder holder, int position) {
        final Reservation reservation = reservations.get(position);
        holder.textView.setText(reservation.getGadget().getName());
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }
}
