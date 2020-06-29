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

public class FreeGemsActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    Button btnGemsToDollar, btnDollarToGems;
    Intent calculateActivityIntent;

    //AdMOb Helper class
    AdMobHelper adMobHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_gems);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        btnGemsToDollar = findViewById(R.id.btnGemsToDollar);
        btnDollarToGems = findViewById(R.id.btnDollarToGems);

        calculateActivityIntent = new Intent(this, CalculateActivity.class);

        //        Mobile ads
        adMobHelper = new AdMobHelper();
        adMobHelper.initInterstitialAd(this);
        adMobHelper.showNativeAd(this, this);

        btnGemsToDollar.setOnClickListener(this);
        btnDollarToGems.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGemsToDollar:
                adMobHelper.showInterstitialAd(new AdMobHelper.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        calculateActivityIntent.putExtra("converter", "gmstodlr");
                        startActivity(calculateActivityIntent);
                    }
                });
                break;
            case R.id.btnDollarToGems:
                adMobHelper.showInterstitialAd(new AdMobHelper.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        calculateActivityIntent.putExtra("converter", "dlrtogms");
                        startActivity(calculateActivityIntent);
                    }
                });
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
                startActivity(new Intent(FreeGemsActivity.this, MenuActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
