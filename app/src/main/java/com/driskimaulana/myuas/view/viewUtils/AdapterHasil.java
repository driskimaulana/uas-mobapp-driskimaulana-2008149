package com.driskimaulana.myuas.view.viewUtils;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.driskimaulana.myuas.R;
import com.driskimaulana.myuas.model.HasilModel;

import java.util.ArrayList;

//untuk recylerview
public class AdapterHasil extends RecyclerView.Adapter<ViewHolderHasil> {

    ArrayList<HasilModel> alHasil;

    public AdapterHasil(ArrayList<HasilModel> alHasil) {
        this.alHasil = alHasil;
    }

    @NonNull
    @Override
    public ViewHolderHasil onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hasil_row, parent, false);
        // jangan sampai lupa return viewholder, akan menyebabkan bug yang susah ditrace
        return new ViewHolderHasil(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHasil holder, int position) {
        HasilModel m = alHasil.get(position);
        holder.tvTimestamp.setText(m.getTimestamp());
        holder.tvDiangkat.setText(m.getAngkatText());
//        holder.tvHasil.setText(m.getHasil());
    }

    @Override
    public int getItemCount() {
        return alHasil.size();
    }
}