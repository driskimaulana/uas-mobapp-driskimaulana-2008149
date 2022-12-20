package com.driskimaulana.myuas.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.driskimaulana.myuas.R;
import com.driskimaulana.myuas.databinding.FragmentDuaBinding;
import com.driskimaulana.myuas.model.HasilModel;
import com.driskimaulana.myuas.view.viewUtils.AdapterHasil;

import java.util.ArrayList;

public class DuaFragment extends Fragment {

    private FragmentDuaBinding binding;

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
}