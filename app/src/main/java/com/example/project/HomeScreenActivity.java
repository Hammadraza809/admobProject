package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;


public class HomeScreenActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    Button btnFreePoints, btnUse , btnFreeCacl;
    private Intent freePointsIntent, useIntent, freeCalIntent;

    //AdMOb Helper class
    AdMobHelper adMobHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        btnFreePoints = findViewById(R.id.freePoints);
        btnUse = findViewById(R.id.howToUse);
        btnFreeCacl = findViewById(R.id.freeCalc);

        //        Mobile ads
        adMobHelper = new AdMobHelper();
        adMobHelper.initInterstitialAd(this);
        adMobHelper.showNativeAd(this, this);

        btnFreePoints.setOnClickListener(this);
        btnUse.setOnClickListener(this);
        btnFreeCacl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.freePoints:
                Intent freePointsIntent = new Intent(HomeScreenActivity.this,FreePointsScreenActivity.class);
                startActivity(freePointsIntent);
                break;
            case R.id.howToUse:
                Intent useIntent = new Intent(HomeScreenActivity.this,UseScreenActivity.class);
                startActivity(useIntent);
                break;
            case R.id.freeCalc:
                Intent calIntent = new Intent(HomeScreenActivity.this,FreeCalculatorScreenActivity.class);
                startActivity(calIntent);
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
                startActivity(new Intent(HomeScreenActivity.this,MenuActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
