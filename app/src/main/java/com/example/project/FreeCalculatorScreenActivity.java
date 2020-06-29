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

public class FreeCalculatorScreenActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    Button btnFreeGems, btnFreeCoins;
    Intent freeGemsIntent, freeCoinsIntent;

    //AdMOb Helper class
    AdMobHelper adMobHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_calculator_screen);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        btnFreeGems = findViewById(R.id.btnFreeGems);
        btnFreeCoins = findViewById(R.id.btnFreeCoins);

        //        Mobile ads
        adMobHelper = new AdMobHelper();
        adMobHelper.initInterstitialAd(this);
        adMobHelper.showNativeAd(this, this);

        btnFreeGems.setOnClickListener(this);
        btnFreeCoins.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnFreeGems:
                adMobHelper.showInterstitialAd(new AdMobHelper.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        freeGemsIntent = new Intent(FreeCalculatorScreenActivity.this,FreeGemsActivity.class);
                        startActivity(freeGemsIntent);
                    }
                });
                break;
            case R.id.btnFreeCoins:
                adMobHelper.showInterstitialAd(new AdMobHelper.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        freeCoinsIntent = new Intent(FreeCalculatorScreenActivity.this, FreeCoinsActivity.class);
                        startActivity(freeCoinsIntent);
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
                startActivity(new Intent(FreeCalculatorScreenActivity.this,MenuActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
