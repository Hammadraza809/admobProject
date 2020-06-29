package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import java.text.DecimalFormat;

public class CalculateActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView tvHeading;
    EditText etAmount;
    Button btnCalculate;

    //AdMOb Helper class
    AdMobHelper adMobHelper;

    double result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvHeading = findViewById(R.id.tvHeading);
        etAmount = findViewById(R.id.etAmount);
        btnCalculate = findViewById(R.id.btnCalculate);

//        Mobile ads
        adMobHelper = new AdMobHelper();
        adMobHelper.showNativeAd(this, this);

        final String heading = getIntent().getStringExtra("converter");
        if (heading.equals("gmstodlr")){
            tvHeading.setText("Gems To Dollar");
        } else if (heading.equals("dlrtogms")){
            tvHeading.setText("Dollar To Gems");
        } else if (heading.equals("coinstodlr")){
            tvHeading.setText("Coins To Dollar");
        } else if (heading.equals("dlrtocoins")){
            tvHeading.setText("Dollar To Coins");
        }

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etAmount.getText().toString().trim().equals("")){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CalculateActivity.this);
                    alertDialogBuilder.setMessage("Please enter any number");
                    alertDialogBuilder.setTitle("Alert!");
                    alertDialogBuilder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } else {
                    if (heading.equals("gmstodlr")){
                        result = (Double.parseDouble(etAmount.getText().toString().trim()) * 0.00018);
                    } else if (heading.equals("dlrtogms")){
                        result = (Double.parseDouble(etAmount.getText().toString().trim()) / 0.00018);
                    } else if (heading.equals("coinstodlr")){
                        result = (Double.parseDouble(etAmount.getText().toString().trim()) * 0.07);
                    } else if (heading.equals("dlrtocoins")){
                        result = (Double.parseDouble(etAmount.getText().toString().trim()) * 0.07);
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(CalculateActivity.this, R.style.CustomDialog);
                    final FrameLayout frameView = new FrameLayout(CalculateActivity.this);
                    builder.setView(frameView);

                    final AlertDialog alertDialog = builder.create();
                    LayoutInflater inflater = alertDialog.getLayoutInflater();
                    View view = inflater.inflate(R.layout.converter_dialog, frameView);

                    Button btnConverter = view.findViewById(R.id.btnConverter);
                    TextView txtResultConverter = view.findViewById(R.id.txtResultConverter);
                    ImageView imgCloseConverter = view.findViewById(R.id.imgCloseConverter);

                    btnConverter.setText(ConstantValues.btnConverter_popup_text);
                    txtResultConverter.setText(new DecimalFormat("##.##").format(result));
                    imgCloseConverter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.cancel();
                        }
                    });
                    btnConverter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            alertDialog.cancel();
                            String url = ConstantValues.btnConverter_popup_link;
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    });
                    alertDialog.show();
                }
            }
        });
    }
}
