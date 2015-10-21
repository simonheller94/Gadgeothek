package com.example.simon.gadgeothek;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.provider.SyncStateContract;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.simon.gadgeothek.fragments.LoanListFragment;
import com.example.simon.gadgeothek.fragments.LoginFragment;
import com.example.simon.gadgeothek.fragments.RegistrationFragment;
import com.example.simon.gadgeothek.fragments.ReservationFragment;
import com.example.simon.gadgeothek.services.Callback;
import com.example.simon.gadgeothek.services.LibraryService;

import java.util.Stack;

public class GadgeothekActivity extends AppCompatActivity implements View.OnClickListener{

    enum Pages {LOGIN, REGISTRATION, AUSLEIH, RESERVATION}

    private Stack<Pages> pages = new Stack<>();

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LibraryService.setServerAddress("http://mge7.dev.ifs.hsr.ch/public");

        fragmentManager = getFragmentManager();

        pages.push(Pages.LOGIN);
        switchTo(new LoginFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.logout){
            LibraryService.logout(new Callback<Boolean>() {
                @Override
                public void onCompletion(Boolean input) {
                    switchTo(new LoginFragment());
                }

                @Override
                public void onError(String message) {

                }
            });
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (pages.peek()) {
            case LOGIN:
                pages.push(Pages.REGISTRATION);
                switchTo(new RegistrationFragment());
                break;
            case REGISTRATION:
                pages.push(Pages.AUSLEIH);
                //switchTo(new LoanListFragment());
                break;
            case AUSLEIH:
                pages.pop();
                pages.push(Pages.RESERVATION);
                switchTo(new ReservationFragment());

                break;
            case RESERVATION:
                pages.pop();
                pages.push(Pages.AUSLEIH);
                //switchTo(new LoanListFragment());
                break;
        }
    }

    public void switchTo(Fragment fragment) {
        Bundle args = new Bundle();
        fragment.setArguments(args);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeholder, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void onBackPressed() {

        if (fragmentManager.getBackStackEntryCount() <= 1) {
            finish();
        } else {
            pages.pop();
            getFragmentManager().popBackStack();
        }
    }

    public void test(){

        //LibraryService.register();

    }


}
