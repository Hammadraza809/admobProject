package com.example.project;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;


public class AdMobHelper {

    InterstitialAd interstitialAd;
    boolean isReloaded = false;
    private AdCloseListener adCloseListener;

    public AdMobHelper() {

    }

//    Initializing Interstitial ad
    public void initInterstitialAd(Context context) {
        MobileAds.initialize(context, ConstantValues.admob_app_id);
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(ConstantValues.interstitial_ad_id);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                if (!isReloaded) {
                    isReloaded = true;
                    loadInterstitialAd();
                }
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                if (adCloseListener != null) {
                    adCloseListener.onAdClosed();
                    loadInterstitialAd();
                }
            }
        });
        loadInterstitialAd();
    }

//    Loading Interstitial Ad
    public void loadInterstitialAd() {
        interstitialAd.loadAd(new AdRequest.Builder().build());
    }

//    Showing Interstitial Ad
    public void showInterstitialAd(AdCloseListener adCloseListener) {
        this.adCloseListener = adCloseListener;
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            adCloseListener.onAdClosed();
        }
    }

//    Handling when ad get closed.
    public interface AdCloseListener {
        void onAdClosed();
    }


    //    Showing Native ad
    public void showNativeAd(Context context, final AppCompatActivity appCompatActivity) {
        MobileAds.initialize(context, ConstantValues.admob_app_id);
        AdLoader builder = new AdLoader.Builder(appCompatActivity, ConstantValues.native_ad_id)
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        TemplateView templateView = appCompatActivity.findViewById(R.id.my_template);
                        templateView.setNativeAd(unifiedNativeAd);
                    }
                })
                .build();
        builder.loadAd(new AdRequest.Builder().build());
    }
}
