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

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private BottomNavigationView bnv;
    private TextView textView;

    private SensorManager sm;
    private Sensor senAccel;

    private boolean isTabrakan;

    private static final int REQUEST_LOCATION = 1;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // check sensor
        sm = (SensorManager) getSystemService(getApplicationContext().SENSOR_SERVICE);
        senAccel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (senAccel != null) {
            Log.d("mydebug", "Success: Device has accelerator sensor.");
        }else {
            Log.d("mydebug", "Failed: Device did not have accelerator sensor.");
        }

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            // show dialog to enable location
            OnGPS();
        }else {
            getLocation();
        }

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

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, senAccel, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                Log.d("mydebug", "lat: " + String.valueOf(locationGPS.getLatitude()) + " | long: " + String.valueOf(locationGPS.getLongitude()));
            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double ax = 0, ay = 0, az = 0;
        // get sensor value
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            ax = sensorEvent.values[0];
            ay = sensorEvent.values[1];
            az = sensorEvent.values[2];
        }

        long timestamp = System.currentTimeMillis();
        // show accelerator log with timestamp
        String msg = "X: " + ax + ", Y: " + ay + ", Z: " + az +", Timestamp: " + timestamp;
        Log.d("mydebug", msg);


        if (az <= -8) {
            isTabrakan = true;
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
//        if (isTabrakan) {
////            textView.setText("Terjadi Tabrakan!");
//        }else {
//            long timestamp = System.currentTimeMillis();
//            // show accelerator log with timestamp
//            String msg = "X: " + ax + ", Y: " + ay + ", Z: " + az +", Timestamp: " + timestamp;
//            Log.d("mydebug", "mgs");
////            textView.setText(msg);
//        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}