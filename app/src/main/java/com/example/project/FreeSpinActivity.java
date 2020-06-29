package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class FreeSpinActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imgSpin;
    Button btnSpin;
    TextView tvSPoints;
    private Random random = new Random();
    int newDir;
    float pivotX, pivotY;

    //AdMOb Helper class
    AdMobHelper adMobHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_spin);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        imgSpin = findViewById(R.id.imgSpin);
        btnSpin = findViewById(R.id.btnSpin);
        tvSPoints = findViewById(R.id.tvSPoints);

        //        Mobile ads
        adMobHelper = new AdMobHelper();
        adMobHelper.showNativeAd(this, this);

//        For Saving points
        final SavingDataActivity dataActivity = (SavingDataActivity) getApplicationContext();

//        Showing total balance points on start of the Activity
        int pts = dataActivity.getPoints();
        String Spts = Integer.toString(pts);
        tvSPoints.setText("You earned: "+Spts+"Points");

        btnSpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newDir = random.nextInt(3600);
                pivotX = (float)imgSpin.getWidth()/2;
                pivotY = (float)imgSpin.getHeight()/2;
                Animation rotate = new RotateAnimation(0,newDir,pivotX,pivotY);
                rotate.setDuration(2500);
                rotate.setFillAfter(true);
                rotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        btnSpin.setEnabled(false);
                        btnSpin.setBackgroundColor(getResources().getColor(R.color.disable));
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Toast.makeText(FreeSpinActivity.this, "You got 10 Points.", Toast.LENGTH_SHORT).show();
                        dataActivity.setPoints(10);
                        btnSpin.setEnabled(true);
                        btnSpin.setBackgroundColor(getResources().getColor(R.color.btn_color));
                        int pts = dataActivity.getPoints();
                        String Spts = Integer.toString(pts);
                        tvSPoints.setText("You earned: "+Spts+"Points");
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                imgSpin.startAnimation(rotate);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu:
                startActivity(new Intent(FreeSpinActivity.this,MenuActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
