package com.example.simon.gadgeothek.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.simon.gadgeothek.GadgeothekActivity;
import com.example.simon.gadgeothek.R;
import com.example.simon.gadgeothek.domain.Gadget;
import com.example.simon.gadgeothek.services.Callback;
import com.example.simon.gadgeothek.services.LibraryService;

import java.util.List;

public class ReservationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_reservation, container, false);

        //root.findViewById(R.id.loginButton).setOnClickListener((View.OnClickListener) getActivity());

        LibraryService.getGadgets(new Callback<List<Gadget>>() {
            @Override
            public void onCompletion(List<Gadget> input) {

            }

            @Override
            public void onError(String message) {

            }
        });

        Spinner dynamicSpinner = (Spinner) root.findViewById(R.id.dynamic_spinner);

        String[] items = new String[] { "Chai Latte", "Green Tea", "Black Tea" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, items);

        dynamicSpinner.setAdapter(adapter);

        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        Button reserve = (Button) root.findViewById(R.id.reservation);
        //Gadget _gadget = (Gadget) root.findViewById(R.id.dynamic_spinner);

        reserve.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                /*LibraryService.reserveGadget(_gadget, new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        if (input) {
                            ((GadgeothekActivity) getActivity()).switchTo(new TabFragment());

                        } else {
                            ((GadgeothekActivity) getActivity()).switchTo(new TabFragment());

                        }

                    }

                    @Override
                    public void onError(String message) {
                        ((GadgeothekActivity) getActivity()).switchTo(new TabFragment());

                    }
                });*/

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
