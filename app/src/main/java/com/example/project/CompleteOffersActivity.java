package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class CompleteOffersActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btnGo;
    private WebView webView;

    //AdMOb Helper class
    AdMobHelper adMobHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_offers);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        btnGo = findViewById(R.id.btnGo);

        //        Mobile ads
        adMobHelper = new AdMobHelper();
        adMobHelper.initInterstitialAd(this);
        adMobHelper.showNativeAd(this, this);

        //WebView Setting and Variables
        webView = findViewById(R.id.webView);
        webView.setVisibility(View.GONE);
        webView.setWebViewClient(new WebViewClient());
        final WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adMobHelper.showInterstitialAd(new AdMobHelper.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        webView.setVisibility(View.VISIBLE);
                        webView.loadUrl(ConstantValues.webView_url);
                        btnGo.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu:
                startActivity(new Intent(CompleteOffersActivity.this, MenuActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
