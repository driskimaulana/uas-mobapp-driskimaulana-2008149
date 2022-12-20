package com.driskimaulana.myuas;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.driskimaulana.myuas.view.DuaFragment;
import com.driskimaulana.myuas.view.SatuFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bnv;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnv = findViewById(R.id.bottomNavigationView);
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.itmSatu:
                        Toast.makeText(MainActivity.this, "Satu", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.fragment_container_view, SatuFragment.class, null)
                                .commit();
                        break;
                    case R.id.itmDua:
                        Toast.makeText(MainActivity.this, "Dua", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.fragment_container_view, DuaFragment.class, null)
                                .commit();
                        break;
                }
                return true;
            }
        });

    }


}