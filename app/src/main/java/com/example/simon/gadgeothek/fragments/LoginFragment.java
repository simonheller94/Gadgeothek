package com.example.simon.gadgeothek.fragments;

import android.app.Activity;
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


public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_login, container, false);

        //toolbar declaration and design
        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        toolbar.setTitle("Login");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        //root.findViewById(R.id.loginButton).setOnClickListener((View.OnClickListener) getActivity());
        root.findViewById(R.id.registrationButton).setOnClickListener((View.OnClickListener) getActivity());

        Button login = (Button) root.findViewById(R.id.loginButton);

        final EditText email = (EditText) root.findViewById(R.id.emailAddressEditText);
        final EditText password = (EditText) root.findViewById(R.id.passwordEditText);

        //Login
        login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                final String _email = email.getText().toString();
                final String _pw = password.getText().toString();

                LibraryService.login(_email, _pw, new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        if (input) {
                            ((GadgeothekActivity) getActivity()).switchTo(new TabFragment());
                            Snackbar.make(root.findViewById(R.id.fragment_login), "Login success", Snackbar.LENGTH_LONG)
                                    .show();

                        } else {
                            Snackbar.make(root.findViewById(R.id.fragment_login), "Logindata is false", Snackbar.LENGTH_LONG)
                                    .show();
                        }
                    }

                    @Override
                    public void onError(String message) {
                        Snackbar.make(root.findViewById(R.id.fragment_login), "Login error", Snackbar.LENGTH_LONG)
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
