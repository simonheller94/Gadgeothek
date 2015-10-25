package com.example.simon.gadgeothek.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
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
        final View root = inflater.inflate(R.layout.fragment_registration, container, false);

        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);

        toolbar.setTitle("Registration");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        //for crate home button
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        root.findViewById(R.id.register).setOnClickListener((View.OnClickListener) getActivity());

        Button register = (Button) root.findViewById(R.id.register);

        final EditText fname = (EditText) root.findViewById(R.id.fname);
        final EditText email = (EditText) root.findViewById(R.id.email);
        final EditText password = (EditText) root.findViewById(R.id.password);
        final EditText mnumber = (EditText) root.findViewById(R.id.mnumber);

        register.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String _fname = fname.getText().toString();
                String _email = email.getText().toString();
                String _pw = password.getText().toString();
                String _mnumber = mnumber.getText().toString();

                LibraryService.register(_email, _pw, _fname, _mnumber, new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        if (input) {
                            ((GadgeothekActivity) getActivity()).switchTo(new LoginFragment());

                        } else {
                            Snackbar.make(root.findViewById(R.id.fragment_registration), "Registrationdata is false", Snackbar.LENGTH_LONG)
                                    .show();

                        }

                    }

                    @Override
                    public void onError(String message) {
                        Snackbar.make(root.findViewById(R.id.fragment_registration), "Registration error", Snackbar.LENGTH_LONG)
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
