package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class WatchVideosActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    Button btnWatch1, btnWatch2, btnWatch3, btnWatch4, btn9pts, btn5pts, btn2pts, btn22pts;
    TextView tvWPoints;
    RewardedVideoAd rewardedVideoAd;
    Boolean videoComplt = false;

    //AdMOb Helper class
    AdMobHelper adMobHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_videos);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //        Mobile ads
        adMobHelper = new AdMobHelper();
        adMobHelper.showNativeAd(this, this);

//        Initializing buttons
        tvWPoints = findViewById(R.id.tvWPoints);
        btnWatch1 = findViewById(R.id.btnWatch1);
        btnWatch2 = findViewById(R.id.btnWatch2);
        btnWatch3 = findViewById(R.id.btnWatch3);
        btnWatch4 = findViewById(R.id.btnWatch4);
        btn9pts = findViewById(R.id.btn9pts);
        btn5pts = findViewById(R.id.btn5pts);
        btn2pts = findViewById(R.id.btn2pts);
        btn22pts = findViewById(R.id.btn22pts);

//        Enabling watch videos buttons
        btnWatch1.setEnabled(true);
        btnWatch2.setEnabled(true);
        btnWatch3.setEnabled(true);
        btnWatch4.setEnabled(true);

//        For saving points.
        final SavingDataActivity dataActivity = (SavingDataActivity) getApplicationContext();

//        Setting on click listener
        btnWatch1.setOnClickListener(this);
        btnWatch2.setOnClickListener(this);
        btnWatch3.setOnClickListener(this);
        btnWatch4.setOnClickListener(this);

        btn22pts.setOnClickListener(this);
        btn2pts.setOnClickListener(this);
        btn5pts.setOnClickListener(this);
        btn9pts.setOnClickListener(this);

//        Showing points when activity started.
        int pts = dataActivity.getPoints();
        String Spts = Integer.toString(pts);
        tvWPoints.setText(Spts + "Points");

//        Rewarded Video add
        MobileAds.initialize(this,ConstantValues.admob_app_id);
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        rewardedVideoAd.loadAd(ConstantValues.reward_video_ad_id, new AdRequest.Builder().build());

//        Setting Rewarded video add.
        rewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {}

            @Override
            public void onRewardedVideoAdOpened() {}

            @Override
            public void onRewardedVideoStarted() {}

            @Override
            public void onRewardedVideoAdClosed() {
//                Checking if user watch complete ad or close in the middle
                if (videoComplt) {
//                    if user watch complete ad than giving user points
                    Toast.makeText(WatchVideosActivity.this, "You got 10 points.", Toast.LENGTH_SHORT).show();
                    rewardedVideoAd.loadAd(ConstantValues.reward_video_ad_id, new AdRequest.Builder().build());
                } else {
//                    if user canceled ad or not watch complete add than not giving user points.
                    Toast.makeText(WatchVideosActivity.this, "You did't get reward.", Toast.LENGTH_SHORT).show();
                    rewardedVideoAd.loadAd(ConstantValues.reward_video_ad_id, new AdRequest.Builder().build());
                }
            }
            @Override
            public void onRewarded(com.google.android.gms.ads.reward.RewardItem rewardItem) {
//                Giving user points.
                dataActivity.setPoints(10);
//                Getting user points for display
                int pts = dataActivity.getPoints();
//                Displaying user realtime points.
                String Spts = Integer.toString(pts);
                tvWPoints.setText(Spts + "Points");
//                loading reward video again
                rewardedVideoAd.loadAd(ConstantValues.reward_video_ad_id, new AdRequest.Builder().build());
            }
            @Override
            public void onRewardedVideoAdLeftApplication() {}

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {}

            @Override
            public void onRewardedVideoCompleted() {
                videoComplt = true;
            }
        });
    }

//    Onclick on buttons
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnWatch1:
            case R.id.btnWatch2:
            case R.id.btnWatch3:
            case R.id.btnWatch4:
                if (rewardedVideoAd.isLoaded()) {
                    rewardedVideoAd.show();
                    v.setEnabled(false);
                    v.setBackgroundColor(getResources().getColor(R.color.disable));
                } else {
                    Toast.makeText(WatchVideosActivity.this, "Ad Not Loaded", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //    Rewarded Video control methods.
    @Override
    public void onResume() {
        rewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        rewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        rewardedVideoAd.destroy(this);
        super.onDestroy();
    }

    //    Toolbar creation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu:
                startActivity(new Intent(WatchVideosActivity.this, MenuActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
