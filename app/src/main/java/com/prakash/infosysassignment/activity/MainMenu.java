package com.prakash.infosysassignment.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import com.prakash.infosysassignment.R;
import com.prakash.infosysassignment.fragment.AboutCanada;

/* Main activity which loads a fragment */

public class MainMenu extends AppCompatActivity {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        init();
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        setCustomTitle(actionBar.getTitle().toString());

        /* In case of orientation changes, we first make sure if we already have a retained instance of fragment.
         * If a fragment is found, we use that. Otherwise we create a new instance of fragment and load. */
        FragmentManager fm = getSupportFragmentManager();
        AboutCanada fragment = (AboutCanada) fm.findFragmentByTag("AboutCanada");
        if (fragment == null)
            fragment = AboutCanada.newInstance(null, null);
        fm.beginTransaction().replace(R.id.content_main_menu, fragment, fragment.getClass().getSimpleName()).commit();
    }

    /* To change the color of title */
    public void setCustomTitle(String title) {
        SpannableString mNewTitle = new SpannableString(title);
        mNewTitle.setSpan(new ForegroundColorSpan(Color.WHITE), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(mNewTitle);
    }

}
