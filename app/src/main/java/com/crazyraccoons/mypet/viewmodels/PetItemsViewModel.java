package com.crazyraccoons.mypet.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.crazyraccoons.mypet.data.model.PetItem;
import com.crazyraccoons.mypet.data.PetItemRepository;

import java.util.List;

public class PetItemsViewModel extends AndroidViewModel {
    private PetItemRepository repository;
    private LiveData<List<PetItem>> petItemsList;

    public PetItemsViewModel(@NonNull Application application) {
        super(application);
        repository = new PetItemRepository(application);
        petItemsList = repository.getAllProfiles();
    }

    public void insert(PetItem petItem){
        repository.insert(petItem);
    }


    public LiveData<List<PetItem>> getAllProfiles(){
        return petItemsList;
    }
}
