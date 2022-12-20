package com.driskimaulana.myuas.view.viewUtils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.driskimaulana.myuas.R;
import com.driskimaulana.myuas.model.BoredModel;

import java.util.ArrayList;

public class AdapterBored extends RecyclerView.Adapter<ViewHolderBored> {

    ArrayList<BoredModel> boreds;

    public AdapterBored(ArrayList<BoredModel> newBoreds){
        this.boreds = newBoreds;
    }


    @NonNull
    @Override
    public ViewHolderBored onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bored_row, parent, false);
        // jangan sampai lupa return viewholder, akan menyebabkan bug yang susah ditrace
        return new ViewHolderBored(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderBored holder, int position) {
        BoredModel m = boreds.get(position);

        holder.tvType.setText(m.getType());
        holder.tvActivity.setText(m.getActivity());

//        TextView tvType = holder.itemView.findViewById(R.id.tvType);
//        TextView tvActivity = holder.itemView.findViewById(R.id.tvActivity);
//
//
//
//        tvType.setText(m.getType());
//        tvActivity.setText(m.getActivity());
    }

    @Override
    public int getItemCount() {
        return boreds.size();
    }
}
