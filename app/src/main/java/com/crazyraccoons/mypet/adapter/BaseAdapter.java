package com.crazyraccoons.mypet.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.crazyraccoons.mypet.data.model.PetItem;

import java.util.Collections;
import java.util.List;

public abstract class BaseAdapter extends RecyclerView.Adapter {

    private List<PetItem> petsList = Collections.emptyList();

    public void setData(List<PetItem> petsList) {
        this.petsList = petsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public abstract MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i);

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((MyViewHolder) viewHolder).bindData(petsList.get(i));
    }

    @Override
    public int getItemCount() {
        return petsList.isEmpty() ? 0 : petsList.size();
    }
}