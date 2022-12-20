package com.driskimaulana.myuas.view;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.driskimaulana.myuas.R;
import com.driskimaulana.myuas.databinding.FragmentDuaBinding;
import com.driskimaulana.myuas.model.HasilModel;
import com.driskimaulana.myuas.view.viewUtils.AdapterHasil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DuaFragment extends Fragment implements SensorEventListener {

    private FragmentDuaBinding binding;

    private SensorManager sm;
    private Sensor senAccel;

    private boolean isDiam = false;

    ArrayList<HasilModel> alHasil = new ArrayList<>();
    AdapterHasil adapter;
    RecyclerView.LayoutManager lm;


    public DuaFragment() {
        // Required empty public constructor
    }

    public static DuaFragment newInstance(String param1, String param2) {
        DuaFragment fragment = new DuaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDuaBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // check sensor
        sm = (SensorManager) getActivity().getSystemService(getActivity().getApplicationContext().SENSOR_SERVICE);
        senAccel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (senAccel != null) {
            Log.d("mydebug_check", "Success: Device has accelerator sensor.");
        }else {
            Log.d("mydebug_check", "Failed: Device did not have accelerator sensor.");
        }

        adapter = new AdapterHasil(alHasil);
        binding.rvHasil.setAdapter(adapter);

        lm = new LinearLayoutManager(getActivity());
        binding.rvHasil.setLayoutManager(lm);

        //supaya ada garis antar row
        binding.rvHasil.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        binding.buttonFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alHasil.add(new HasilModel("satu..."));
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double ax=0,ay=0,az=0;
        Log.d("sensor_debug", "SENSOR");
        // menangkap perubahan nilai sensor
        if (sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
            ax=sensorEvent.values[0];
            ay=sensorEvent.values[1];
            az=sensorEvent.values[2];
        }



        if  (ay > 0.7 && ax > 0.2 && az > 0.7 && isDiam == true) {
//            if (isDiam != true){
                SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss");
                String ts = s.format(new Date());
                alHasil.add(new HasilModel(ts));
                adapter.notifyDataSetChanged();
                isDiam = false;
//            }
        }

        if (ay < 0.7){
            isDiam = true;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        sm.registerListener(this, senAccel, SensorManager.SENSOR_DELAY_NORMAL);
    }

}