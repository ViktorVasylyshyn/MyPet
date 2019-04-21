package com.crazyraccoons.mypet.adapter;


import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.crazyraccoons.mypet.databinding.PetItemExtendedBinding;

public class PetsExtendedListAdapter extends BaseAdapter {

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        PetItemExtendedBinding binding = PetItemExtendedBinding.inflate(
                LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new MyViewHolder(binding);
    }
}
