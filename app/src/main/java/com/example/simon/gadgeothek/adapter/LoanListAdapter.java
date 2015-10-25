package com.example.simon.gadgeothek.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.simon.gadgeothek.R;
import com.example.simon.gadgeothek.domain.Loan;
import com.example.simon.gadgeothek.domain.Reservation;

import java.util.List;

public class LoanListAdapter extends RecyclerView.Adapter<LoanListViewHolder> {

    private List<Loan> loans;

    public LoanListAdapter(List<Loan> loans) {

        this.loans = loans;
    }

    @Override
    public LoanListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.loanlist_rowlayout, parent, false);
        TextView textView = (TextView) v.findViewById(R.id.loanlist);
        LoanListViewHolder viewHolder = new LoanListViewHolder(v, textView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LoanListViewHolder holder, int position) {
        final Loan loan = loans.get(position);
        holder.textView.setText(loan.getGadget().getName());
    }

    @Override
    public int getItemCount() {
        return loans.size();
    }
}
