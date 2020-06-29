package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class MainActivity extends AppCompatActivity {
    int progressStatus = 0;
    ProgressBar progressBar;
    Handler handler = new Handler();

    //Firebase Variable
    FirebaseRemoteConfig firebaseRemoteConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //SettingUp Firebase
        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings remoteConfigSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(0)
                .build();
        firebaseRemoteConfig.setConfigSettingsAsync(remoteConfigSettings);
        fetchValues();

        //Splash Screen
        threadInit();
    }

    //Fetching firebase values
    public void fetchValues(){
        firebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if (task.isSuccessful()){
                            settingValues();
                        }else {
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //SettingUp fetched values from firebase.
    public void settingValues(){
        ConstantValues.admob_app_id = firebaseRemoteConfig.getString("admob_app_id");
        ConstantValues.native_ad_id = firebaseRemoteConfig.getString("native_ad_id");
        ConstantValues.interstitial_ad_id = firebaseRemoteConfig.getString("interstitial_ad_id");
        ConstantValues.reward_video_ad_id = firebaseRemoteConfig.getString("reward_video_ad_id");
        ConstantValues.install_app_btn1 = firebaseRemoteConfig.getString("install_app_btn1");
        ConstantValues.install_app_btn2 = firebaseRemoteConfig.getString("install_app_btn2");
        ConstantValues.install_app_btn3 = firebaseRemoteConfig.getString("install_app_btn3");
        ConstantValues.install_app_btn4 = firebaseRemoteConfig.getString("install_app_btn4");
        ConstantValues.install_app_img1 = firebaseRemoteConfig.getString("install_app_img1");
        ConstantValues.install_app_img2 = firebaseRemoteConfig.getString("install_app_img2");
        ConstantValues.install_app_img3 = firebaseRemoteConfig.getString("install_app_img3");
        ConstantValues.install_app_img4 = firebaseRemoteConfig.getString("install_app_img4");
        ConstantValues.webView_url = firebaseRemoteConfig.getString("webView_url");
        ConstantValues.btnConverter_popup_text = firebaseRemoteConfig.getString("btnConverter_popup_text");
        ConstantValues.btnConverter_popup_link = firebaseRemoteConfig.getString("btnConverter_popup_link");
        ConstantValues.gdpr_publisher_id = firebaseRemoteConfig.getString("gdpr_publisher_id");
        ConstantValues.gdpr_privacypolicy_url = firebaseRemoteConfig.getString("gdpr_privacypolicy_url");
    }

    //splash screen
    public void threadInit(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 6;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        Thread.sleep(1000);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                startActivity(new Intent(MainActivity.this,StartScreenActivity.class));
                finish();
            }
        }).start();

    }

}
