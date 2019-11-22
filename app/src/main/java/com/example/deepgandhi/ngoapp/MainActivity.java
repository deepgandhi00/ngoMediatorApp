package com.example.deepgandhi.ngoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.deepgandhi.ngoapp.Utils.SharedPreferenceHelper;
import com.example.deepgandhi.ngoapp.fragments.HomeFragment;
import com.example.deepgandhi.ngoapp.fragments.SplashFragment;

public class MainActivity extends AppCompatActivity {

    public static final int ACCESS_FINE = 9999;
    public static final int ACCESS_COARSE = 9998;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                    ACCESS_FINE);

        }
        else {
            SharedPreferenceHelper helper = new SharedPreferenceHelper(this);
            if(helper.checkData()){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new HomeFragment())
                        .commit();
            }
            else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new SplashFragment())
                        .commit();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new SplashFragment())
                    .commit();
        }
        else {
            Toast.makeText(this, "please grant the permission", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.mainLogout){
            SharedPreferenceHelper helper = new SharedPreferenceHelper(this);
            if(helper.removeUser()) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new SplashFragment())
                        .commit();
            }
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}
