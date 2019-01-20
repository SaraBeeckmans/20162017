package com.example.sara.a20162017;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class ProgressActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // timer for seekbar
        final int time = 1 * 30 * 1000; // 30 sec in milli seconds

        /** CountDownTimer starts with 1 minutes and every onTick is 1 second */
        new CountDownTimer(time, 1000) {
            public void onTick(long millisUntilFinished) {

                //forward progress
                long finishedSeconds = time - millisUntilFinished;
                int total = (int) (((float)finishedSeconds / (float)time) * 100.0);
                progressBar.setProgress(total);

//                //backward progress
//                int total = (int) (((float) millisUntilFinished / (float) oneMin) * 100.0);
//                progressBar.setProgress(total);

            }

            public void onFinish() {
                // DO something when 1 minute is up
                Intent game = new Intent(ProgressActivity.this, GameActivity.class);
                startActivity(game);
            }
        }.start();
    }
}

