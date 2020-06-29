package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RateAppActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    TextView tvRPoints;
    Button btnRateApp, btnClaimPoints;

    //AdMOb Helper class
    AdMobHelper adMobHelper;

    SavingDataActivity dataActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_app);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        btnRateApp = findViewById(R.id.btnRate);
        btnClaimPoints = findViewById(R.id.btnClaimPoints);
        tvRPoints = findViewById(R.id.tvWPoints);

//        Saving data
        dataActivity = (SavingDataActivity) getApplicationContext();

//        Showing total balance on starting activity.
        int pts = dataActivity.getPoints();
        String Spts = Integer.toString(pts);
        tvRPoints.setText("Your Points: " + Spts);

//        enabling th button on starting of activity
        btnClaimPoints.setEnabled(true);

        //        Mobile ads
        adMobHelper = new AdMobHelper();
        adMobHelper.showNativeAd(this, this);

        btnRateApp.setOnClickListener(this);
        btnClaimPoints.setOnClickListener(this);
    }

//    Buttons
@Override
public void onClick(View v) {
    switch (v.getId()) {
        case R.id.btnRate:
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
            }
            break;

        case R.id.btnClaimPoints:
            //                Giving user point
            dataActivity.setPoints(10);
            Toast.makeText(RateAppActivity.this, "You got 10 points.", Toast.LENGTH_SHORT).show();
//                Saving user point
            int pts = dataActivity.getPoints();
//                Showing realtime user point increment
            String Spts = Integer.toString(pts);
            tvRPoints.setText("Your Points: " + Spts);
//                Disabling button after user claimed point on one time.
            btnClaimPoints.setEnabled(false);
            break;
    }
}

//    Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu:
                startActivity(new Intent(RateAppActivity.this, MenuActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
