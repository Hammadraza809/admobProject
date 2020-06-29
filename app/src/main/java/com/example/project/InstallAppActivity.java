package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
public class InstallAppActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    Button btnInstall1,btnInstall2,btnInstall3,btnInstall4;
    ImageView img1,img2,img3,img4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install_app);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        btnInstall1 = findViewById(R.id.btnInstall1);
        btnInstall2 = findViewById(R.id.btnInstall2);
        btnInstall3 = findViewById(R.id.btnInstall3);
        btnInstall4 = findViewById(R.id.btnInstall4);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);

        btnInstall1.setOnClickListener(this);
        btnInstall2.setOnClickListener(this);
        btnInstall3.setOnClickListener(this);
        btnInstall4.setOnClickListener(this);

        //Loading install button images from firebase.
        Picasso.get().load(ConstantValues.install_app_img1).into(img1);
        Picasso.get().load(ConstantValues.install_app_img2).into(img2);
        Picasso.get().load(ConstantValues.install_app_img3).into(img3);
        Picasso.get().load(ConstantValues.install_app_img4).into(img4);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnInstall1:
                Intent btn1Intent = new Intent(Intent.ACTION_VIEW);
                btn1Intent.setData(Uri.parse(ConstantValues.install_app_btn1));
                startActivity(btn1Intent);
                break;
            case R.id.btnInstall2:
                Intent btn2Intent = new Intent(Intent.ACTION_VIEW);
                btn2Intent.setData(Uri.parse(ConstantValues.install_app_btn2));
                startActivity(btn2Intent);
                break;
            case R.id.btnInstall3:
                Intent btn3Intent = new Intent(Intent.ACTION_VIEW);
                btn3Intent.setData(Uri.parse(ConstantValues.install_app_btn3));
                startActivity(btn3Intent);
                break;
            case R.id.btnInstall4:
                Intent btn4Intent = new Intent(Intent.ACTION_VIEW);
                btn4Intent.setData(Uri.parse(ConstantValues.install_app_btn4));
                startActivity(btn4Intent);
                break;
        }
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
                startActivity(new Intent(InstallAppActivity.this,MenuActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
