package com.example.simon.gadgeothek.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import com.example.simon.gadgeothek.R;
import com.example.simon.gadgeothek.adapter.ReservationListAdapter;
import com.example.simon.gadgeothek.domain.Reservation;
import com.example.simon.gadgeothek.services.Callback;
import com.example.simon.gadgeothek.services.LibraryService;

public class ReservationListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReservationListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_reservation_list, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        final ArrayList<Reservation> reservations = new ArrayList<>();

        LibraryService.getReservationsForCustomer(new Callback<List<Reservation>>() {
            @Override
            public void onCompletion(List<Reservation> input) {
                for (int i = 0; i < input.size(); i++) {
                    reservations.add(input.get(i));
                }

                adapter = new ReservationListAdapter(reservations);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(String message) {
                Snackbar.make(root.findViewById(R.id.fragment_reservationlist), "Error", Snackbar.LENGTH_LONG)
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
