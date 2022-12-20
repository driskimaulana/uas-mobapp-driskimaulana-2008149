package com.driskimaulana.myuas.view.viewUtils;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.driskimaulana.myuas.R;

public class ViewHolderHasil extends RecyclerView.ViewHolder {
    public TextView tvHasil;

    public ViewHolderHasil(@NonNull View itemView) {
        super(itemView);
        tvHasil = itemView.findViewById(R.id.tvRow);
    }
}