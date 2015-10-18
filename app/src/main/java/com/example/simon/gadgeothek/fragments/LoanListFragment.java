package com.example.simon.gadgeothek.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.simon.gadgeothek.R;
import com.example.simon.gadgeothek.services.LibraryService;


public class LoanListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_loan_list, container, false);

                //Test false or true if logged in
        RelativeLayout relativeLayout = (RelativeLayout) root.findViewById(R.id.loanlist);
        TextView view = new TextView(getActivity());
        view.setText(String.valueOf(LibraryService.isLoggedIn()));
        relativeLayout.addView(view);

        //root.findViewById(R.id.loginButton).setOnClickListener((View.OnClickListener) getActivity());
        //root.findViewById(R.id.registrationButton).setOnClickListener((View.OnClickListener) getActivity());

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
