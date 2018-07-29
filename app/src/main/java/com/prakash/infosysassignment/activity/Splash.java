package com.prakash.infosysassignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.prakash.infosysassignment.R;

/* Splash page */

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        init();
    }

    private void init() {
        Handler handler = new Handler();
        handler.postDelayed(runnable, 3000);
    }

    /* Move to next screen after 3 seconds */
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(Splash.this, MainMenu.class));
            finish();
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        }
    };
}
