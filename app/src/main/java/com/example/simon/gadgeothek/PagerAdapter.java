package com.example.simon.gadgeothek;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.example.simon.gadgeothek.fragments.LoanListFragment;
import com.example.simon.gadgeothek.fragments.ReservationListFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                LoanListFragment tab1 = new LoanListFragment();
                return tab1;
            case 1:
                ReservationListFragment tab2 = new ReservationListFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}