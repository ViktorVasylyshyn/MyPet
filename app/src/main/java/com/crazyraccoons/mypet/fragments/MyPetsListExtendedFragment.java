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
import com.crazyraccoons.mypet.adapter.PetsExtendedListAdapter;
import com.crazyraccoons.mypet.databinding.MyPetsListExtendedFragmentBinding;
import com.crazyraccoons.mypet.viewmodels.PetItemsViewModel;

import static com.crazyraccoons.mypet.utils.NetworkUtil.hasInternetConnection;

public class MyPetsListExtendedFragment extends BaseFragment {

    PetsExtendedListAdapter mAdapter = new PetsExtendedListAdapter();

    private MyPetsListExtendedFragmentBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.my_pets_list_extended_fragment, container, false);
        mBinding.setLifecycleOwner(this);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRV();
        mBinding.setShowProgress(mShowProgress);

        obtainData();
    }

    private void obtainData() {
        if (hasInternetConnection(requireContext())) {
            showProgress(true);
            PetItemsViewModel mPetItemsViewModel = ViewModelProviders.of(requireActivity()).get(PetItemsViewModel.class);
            mPetItemsViewModel.getAllProfiles().observe(this, profiles -> {
                mAdapter.setData(profiles);
                showProgress(false);
            });
        } else {
            showToast(R.string.no_internet_connection);
        }
    }


    private void initRV() {
        mBinding.myPetsListExtendedRecyclerView.setAdapter(mAdapter);
    }
}
