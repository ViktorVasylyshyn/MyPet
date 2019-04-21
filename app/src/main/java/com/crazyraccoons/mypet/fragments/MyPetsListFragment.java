package com.crazyraccoons.mypet.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazyraccoons.mypet.R;
import com.crazyraccoons.mypet.adapter.PetsListAdapter;
import com.crazyraccoons.mypet.databinding.MyPetsListFragmentBinding;
import com.crazyraccoons.mypet.viewmodels.PetItemsViewModel;

public class MyPetsListFragment extends BaseFragment {

    PetsListAdapter mAdapter = new PetsListAdapter();

    private MyPetsListFragmentBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.my_pets_list_fragment, container, false);
        mBinding.setLifecycleOwner(this);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initRV();

        obtainData();
    }

    private void obtainData() {
        showProgress(true);
        PetItemsViewModel mPetItemsViewModel = ViewModelProviders.of(requireActivity()).get(PetItemsViewModel.class);
        mPetItemsViewModel.getAllProfiles().observe(this, profiles -> {
            mAdapter.setData(profiles);
            showProgress(false);
        });
    }

    private void initRV() {
        mBinding.myPetsListRecyclerView.setAdapter(mAdapter);
    }
}
