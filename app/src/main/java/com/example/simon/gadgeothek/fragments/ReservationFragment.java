package com.example.simon.gadgeothek.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.simon.gadgeothek.GadgeothekActivity;
import com.example.simon.gadgeothek.adapter.ReservationAdapter;
import com.example.simon.gadgeothek.R;
import com.example.simon.gadgeothek.domain.Gadget;
import com.example.simon.gadgeothek.services.Callback;
import com.example.simon.gadgeothek.services.LibraryService;

import java.util.ArrayList;
import java.util.List;

public class ReservationFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReservationAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_reservation, container, false);

        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        toolbar.setTitle("Reservation");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        final ArrayList<Gadget> gadgets = new ArrayList<>();


        LibraryService.getGadgets(new Callback<List<Gadget>>() {
            @Override
            public void onCompletion(List<Gadget> input) {
                for (int i = 0; i < input.size(); i++) {
                    gadgets.add(input.get(i));
                }

                adapter = new ReservationAdapter(gadgets);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onError(String message) {
                Snackbar.make(root.findViewById(R.id.fragment_reservation), "Error", Snackbar.LENGTH_LONG)
                        .show();
            }
        });

        Button reserve = (Button) root.findViewById(R.id.reservation);

        reserve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Gadget data = null;

                List<Gadget> gadgetlist = ((ReservationAdapter) adapter).getGadgets();

                for(int i = 0; i < gadgetlist.size(); i++){
                    Gadget gadget = gadgetlist.get(i);
                    if (gadget.isSelected()){
                        data = gadget;
                    }
                }



                LibraryService.reserveGadget(data, new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        if (input) {
                            ((GadgeothekActivity) getActivity()).pages.push(GadgeothekActivity.Pages.TAB);
                            ((GadgeothekActivity) getActivity()).switchTo(new TabFragment());

                        } else {
                            Snackbar.make(root.findViewById(R.id.fragment_reservation), "couldnt reserve gadget", Snackbar.LENGTH_LONG)
                                    .show();

                        }

                    }

                    @Override
                    public void onError(String message) {
                        Snackbar.make(root.findViewById(R.id.fragment_reservation), "error", Snackbar.LENGTH_LONG)
                                .show();

                    }
                });

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
