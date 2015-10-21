package com.example.simon.gadgeothek.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.simon.gadgeothek.GadgeothekActivity;
import com.example.simon.gadgeothek.R;
import com.example.simon.gadgeothek.services.Callback;
import com.example.simon.gadgeothek.services.LibraryService;


public class RegistrationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_registration, container, false);

        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);

        toolbar.setTitle("Registration");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        //for crate home button
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        root.findViewById(R.id.register).setOnClickListener((View.OnClickListener) getActivity());

        Button register = (Button) root.findViewById(R.id.register);

        EditText fname = (EditText) root.findViewById(R.id.fname);
        EditText email = (EditText) root.findViewById(R.id.email);
        EditText adress = (EditText) root.findViewById(R.id.adress);
        EditText password = (EditText) root.findViewById(R.id.password);
        EditText mnumber = (EditText) root.findViewById(R.id.mnumber);

        final String _fname = fname.getText().toString();
        final String _email = email.getText().toString();
        final String _adress = adress.getText().toString();
        final String _pw = password.getText().toString();
        final String _mnumber = mnumber.getText().toString();

        register.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                LibraryService.register(_email, _pw, _fname, _mnumber, new Callback<Boolean>() {
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
