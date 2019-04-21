package com.crazyraccoons.mypet.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.crazyraccoons.mypet.databinding.PetItemBinding;

public class PetsListAdapter extends BaseAdapter {
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        PetItemBinding binding = PetItemBinding.inflate(
                LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new MyViewHolder(binding);
    }
}
