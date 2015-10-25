package com.example.simon.gadgeothek.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.example.simon.gadgeothek.R;
import com.example.simon.gadgeothek.adapter.LoanListAdapter;
import com.example.simon.gadgeothek.adapter.ReservationListAdapter;
import com.example.simon.gadgeothek.domain.Loan;
import com.example.simon.gadgeothek.domain.Reservation;
import com.example.simon.gadgeothek.services.Callback;
import com.example.simon.gadgeothek.services.LibraryService;

import java.util.ArrayList;
import java.util.List;


public class LoanListFragment extends Fragment {

    private RecyclerView recyclerView;
    private LoanListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_loan_list, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        final ArrayList<Loan> loans = new ArrayList<>();

        LibraryService.getLoansForCustomer(new Callback<List<Loan>>() {
            @Override
            public void onCompletion(List<Loan> input) {
                for (int i = 0; i < input.size(); i++) {
                    loans.add(input.get(i));
                }

                adapter = new LoanListAdapter(loans);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(String message) {
                Snackbar.make(root.findViewById(R.id.fragment_loanlist), "Error", Snackbar.LENGTH_LONG)
                        .show();

            }
        });




        return root;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof View.OnClickListener)) {
            throw new AssertionError("Activity must implement View.OnClickListener!");
        }
    }


}
