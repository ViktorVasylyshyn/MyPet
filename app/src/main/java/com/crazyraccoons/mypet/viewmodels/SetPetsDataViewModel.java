package com.crazyraccoons.mypet.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.crazyraccoons.mypet.data.model.PetItem;
import com.crazyraccoons.mypet.viewmodels.setpetdata.SetPetsDataHelper;

public class SetPetsDataViewModel extends ViewModel {

    private SetPetsDataHelper mSetPetItemRepository;

    private MutableLiveData<PetItem> mPetItem = new MutableLiveData<>();

    public SetPetsDataViewModel() {
        mSetPetItemRepository = new SetPetsDataHelper();
        mPetItem.setValue(mSetPetItemRepository.initNewPetItem());
    }

    public void setPetGender() {
        if (mPetItem.getValue() != null) {
            mPetItem.setValue(mSetPetItemRepository.setPetGender(mPetItem.getValue()));
        }
    }

    public void setPetSort() {
        if (mPetItem.getValue() != null) {
            mPetItem.setValue(mSetPetItemRepository.setPetSort(mPetItem.getValue()));
        }
    }

    public LiveData<PetItem> getPetItem() {
        return mPetItem;
    }
}

