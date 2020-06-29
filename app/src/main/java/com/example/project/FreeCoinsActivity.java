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

public class FreeCoinsActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    Button btnCoinsToDollar, btnDollarToCoins;
    Intent calculateActivityIntent;

    //AdMOb Helper class
    AdMobHelper adMobHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_coins);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        btnCoinsToDollar = findViewById(R.id.btnCoinsToDollar);
        btnDollarToCoins = findViewById(R.id.btnDollarToCoins);

//        Mobile ads
        adMobHelper = new AdMobHelper();
        adMobHelper.initInterstitialAd(this);
        adMobHelper.showNativeAd(this, this);

        calculateActivityIntent = new Intent(this, CalculateActivity.class);

        btnCoinsToDollar.setOnClickListener(this);
        btnDollarToCoins.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCoinsToDollar:
                adMobHelper.showInterstitialAd(new AdMobHelper.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        calculateActivityIntent.putExtra("converter", "coinstodlr");
                        startActivity(calculateActivityIntent);
                    }
                });
                break;
            case R.id.btnDollarToCoins:
                adMobHelper.showInterstitialAd(new AdMobHelper.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        calculateActivityIntent.putExtra("converter", "dlrtocoins");
                        startActivity(calculateActivityIntent);
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
                startActivity(new Intent(FreeCoinsActivity.this,MenuActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
