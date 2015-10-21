package com.example.simon.gadgeothek.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.example.simon.gadgeothek.R;
import com.example.simon.gadgeothek.services.LibraryService;


public class LoanListFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_loan_list, container, false);

        //Test false or true if logged in
        RelativeLayout relativeLayout = (RelativeLayout) root.findViewById(R.id.loanlist);
        TextView view = new TextView(getActivity());
        view.setText(String.valueOf(LibraryService.isLoggedIn()));
        relativeLayout.addView(view);


        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);


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
