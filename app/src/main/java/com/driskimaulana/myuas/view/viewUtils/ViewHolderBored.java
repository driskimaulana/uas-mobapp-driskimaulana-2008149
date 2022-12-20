package com.driskimaulana.myuas.view.viewUtils;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.driskimaulana.myuas.R;

public class ViewHolderBored  extends RecyclerView.ViewHolder {
    public TextView tvType;
    public TextView tvActivity;

    public ViewHolderBored(@NonNull View itemView) {
        super(itemView);
        tvType = itemView.findViewById(R.id.tvType);
        tvActivity = itemView.findViewById(R.id.tvActivity);
    }
}
