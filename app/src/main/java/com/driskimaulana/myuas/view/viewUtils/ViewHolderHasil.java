package com.driskimaulana.myuas.view.viewUtils;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.driskimaulana.myuas.R;

public class ViewHolderHasil extends RecyclerView.ViewHolder {
    public TextView tvTimestamp;
    public TextView tvDiangkat;

    public ViewHolderHasil(@NonNull View itemView) {
        super(itemView);
        tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
        tvDiangkat = itemView.findViewById(R.id.tvDiangkat);
//        tvHasil = itemView.findViewById(R.id.tvRow);
    }
}