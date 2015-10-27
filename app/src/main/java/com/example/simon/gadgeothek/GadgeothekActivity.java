package com.example.simon.gadgeothek;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.simon.gadgeothek.fragments.LoginFragment;
import com.example.simon.gadgeothek.fragments.RegistrationFragment;
import com.example.simon.gadgeothek.fragments.TabFragment;
import com.example.simon.gadgeothek.services.Callback;
import com.example.simon.gadgeothek.services.LibraryService;

import java.util.Stack;

public class GadgeothekActivity extends AppCompatActivity implements View.OnClickListener{

    public enum Pages {LOGIN, REGISTRATION, TAB, RESERVATION}
    public Stack<Pages> pages = new Stack<>();

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //define ServerAddress for pre-defined users, etc.
        LibraryService.setServerAddress("http://mge7.dev.ifs.hsr.ch/public");

        fragmentManager = getFragmentManager();

        //start with the loginfragment
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

        //logoutbutton in toolbar
        if (id == R.id.logout){
            LibraryService.logout(new Callback<Boolean>() {
                @Override
                public void onCompletion(Boolean input) {
                    pages.clear();
                    pages.push(Pages.LOGIN);
                    switchTo(new LoginFragment());
                }

                @Override
                public void onError(String message) {
                    Snackbar.make(findViewById(R.id.placeholder), "Logout error", Snackbar.LENGTH_LONG)
                            .show();

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
        }
    }

    //switch fragments
    public void switchTo(Fragment fragment) {
        Bundle args = new Bundle();
        fragment.setArguments(args);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeholder, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {

        if(pages.peek() == Pages.REGISTRATION){
            pages.pop();
            switchTo(new LoginFragment());
        }else if(pages.peek() == Pages.RESERVATION) {
            pages.pop();
            switchTo(new TabFragment());
        }else if(pages.peek() == Pages.TAB){
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       GadgeothekActivity.this.finish();
                            }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }else {
            if (fragmentManager.getBackStackEntryCount() <= 1) {
                finish();
            } else {
                pages.pop();
                getFragmentManager().popBackStack();
            }
        }
    }


}
