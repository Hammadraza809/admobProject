package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class FreePointsScreenActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    ImageView imgFreeSpin, imgWatchVid ,imgRateApp, imgInstallApp, imgComplOffer, imgReferFrnd;
    Intent freeSpinIntent, watchVidIntent, rateAppIntent, installAppIntent, compltOffrIntent, referFrndIntent;

    //AdMOb Helper class
    AdMobHelper adMobHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_points_screen);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imgFreeSpin = findViewById(R.id.imgFreeSpin);
        imgWatchVid = findViewById(R.id.imgWatchVid);
        imgRateApp = findViewById(R.id.imgRateApp);
        imgInstallApp = findViewById(R.id.imgInstallApp);
        imgComplOffer = findViewById(R.id.imgComplOffer);
        imgReferFrnd = findViewById(R.id.imgReferFrnd);

        //        Mobile ads
        adMobHelper = new AdMobHelper();
        adMobHelper.initInterstitialAd(this);

        imgFreeSpin.setOnClickListener(this);
        imgWatchVid.setOnClickListener(this);
        imgRateApp.setOnClickListener(this);
        imgInstallApp.setOnClickListener(this);
        imgComplOffer.setOnClickListener(this);
        imgReferFrnd.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgFreeSpin:
                adMobHelper.showInterstitialAd(new AdMobHelper.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        freeSpinIntent = new Intent(FreePointsScreenActivity.this,FreeSpinActivity.class);
                        startActivity(freeSpinIntent);
                    }
                });
                break;
            case R.id.imgWatchVid:
                adMobHelper.showInterstitialAd(new AdMobHelper.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        watchVidIntent = new Intent(FreePointsScreenActivity.this,WatchVideosActivity.class);
                        startActivity(watchVidIntent);
                    }
                });
                break;
            case R.id.imgRateApp:
                adMobHelper.showInterstitialAd(new AdMobHelper.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        rateAppIntent = new Intent(FreePointsScreenActivity.this,RateAppActivity.class);
                        startActivity(rateAppIntent);
                    }
                });
                break;
            case R.id.imgInstallApp:
                adMobHelper.showInterstitialAd(new AdMobHelper.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        installAppIntent = new Intent(FreePointsScreenActivity.this,InstallAppActivity.class);
                        startActivity(installAppIntent);
                    }
                });
                break;
            case R.id.imgComplOffer:
                adMobHelper.showInterstitialAd(new AdMobHelper.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        compltOffrIntent = new Intent(FreePointsScreenActivity.this,CompleteOffersActivity.class);
                        startActivity(compltOffrIntent);
                    }
                });
                break;
            case R.id.imgReferFrnd:
                adMobHelper.showInterstitialAd(new AdMobHelper.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        referFrndIntent = new Intent(FreePointsScreenActivity.this,ReferAFriendActivity.class);
                        startActivity(referFrndIntent);
                    }
                });
                break;
        }
    }

//    Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu:
                startActivity(new Intent(FreePointsScreenActivity.this,MenuActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
