package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


public class StartScreenActivity extends AppCompatActivity implements View.OnClickListener {
    CheckBox chkBox;
    Button btnStart;
    TextView tvPrivacy, tvTermsServices;

    //AdMOb Helper class
    AdMobHelper adMobHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        chkBox = findViewById(R.id.chkBox);
        btnStart = findViewById(R.id.btnStart);
        tvPrivacy = findViewById(R.id.tvPrivacy);
        tvTermsServices = findViewById(R.id.tvTermsServices);

//        Mobile ads
        adMobHelper = new AdMobHelper();
        adMobHelper.initInterstitialAd(this);
        adMobHelper.showNativeAd(this, this);

//        GDPR Show
        GdprHelper gdprHelper = new GdprHelper(this);
        gdprHelper.initialise();

        tvPrivacy.setOnClickListener(this);
        tvTermsServices.setOnClickListener(this);
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            TextView Privacy Policy On Click
            case R.id.tvPrivacy:
                AlertDialog.Builder tvPrivacyDialog = new AlertDialog.Builder(StartScreenActivity.this);
                tvPrivacyDialog.setTitle("Privacy Policy");
                tvPrivacyDialog.setMessage("This is our Privacy Policy. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis rutrum gravida arcu, non tristique leo placerat id. In hac habitasse platea dictumst. Pellentesque vel tempus arcu. Nunc condimentum purus nec magna luctus venenatis. Pellentesque mattis eu nisi quis faucibus. Maecenas aliquam ex leo, nec placerat lectus eleifend quis. Sed justo sapien, tempor suscipit sollicitudin non, feugiat sed ex. Donec neque turpis, pellentesque sed convallis rutrum, ultricies vel nisl. Duis sed nisl eu tellus viverra condimentum sit amet tristique tellus. Vivamus ex massa, sodales sit amet condimentum quis, molestie quis purus. Phasellus aliquet, nisi consequat condimentum dapibus, sapien velit tincidunt magna, at viverra ipsum arcu ac ex");
                tvPrivacyDialog.setPositiveButton("Agree",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });
                AlertDialog tvPrivacyAlertDialog = tvPrivacyDialog.create();
                tvPrivacyAlertDialog.show();
                break;
//                Text View Terms of Services On CLick
            case R.id.tvTermsServices:
                AlertDialog.Builder tvTermsDialog = new AlertDialog.Builder(StartScreenActivity.this);
                tvTermsDialog.setTitle("Terms of Services.");
                tvTermsDialog.setMessage("This is our Terms of Services. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis rutrum gravida arcu, non tristique leo placerat id. In hac habitasse platea dictumst. Pellentesque vel tempus arcu. Nunc condimentum purus nec magna luctus venenatis. Pellentesque mattis eu nisi quis faucibus. Maecenas aliquam ex leo, nec placerat lectus eleifend quis. Sed justo sapien, tempor suscipit sollicitudin non, feugiat sed ex. Donec neque turpis, pellentesque sed convallis rutrum, ultricies vel nisl. Duis sed nisl eu tellus viverra condimentum sit amet tristique tellus. Vivamus ex massa, sodales sit amet condimentum quis, molestie quis purus. Phasellus aliquet, nisi consequat condimentum dapibus, sapien velit tincidunt magna, at viverra ipsum arcu ac ex");
                tvTermsDialog.setPositiveButton("Agree",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });
                AlertDialog tvTermsAlertDialog = tvTermsDialog.create();
                tvTermsAlertDialog.show();
                break;
//                Button start on click
            case R.id.btnStart:
                if (chkBox.isChecked()) {
                    adMobHelper.showInterstitialAd(new AdMobHelper.AdCloseListener() {
                        @Override
                        public void onAdClosed() {
                            startActivity(new Intent(StartScreenActivity.this, HomeScreenActivity.class));
                        }
                    });
                } else {
                    Toast.makeText(StartScreenActivity.this, "Please agree to our privacy policy and terms of services.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        System.exit(1);
    }
}
