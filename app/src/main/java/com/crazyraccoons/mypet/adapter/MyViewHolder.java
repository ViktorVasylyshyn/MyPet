package com.crazyraccoons.mypet.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.crazyraccoons.mypet.data.model.PetItem;
import com.crazyraccoons.mypet.databinding.PetItemBinding;
import com.crazyraccoons.mypet.databinding.PetItemExtendedBinding;

class MyViewHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding mBinding;

    MyViewHolder(ViewDataBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }

    void bindData(PetItem petItem) {
        if (mBinding instanceof PetItemBinding) {
            ((PetItemBinding) mBinding).setPetItem(petItem);
        } else if (mBinding instanceof PetItemExtendedBinding) {
            ((PetItemExtendedBinding) mBinding).setPetItemExtended(petItem);
        }
    }
}
