package com.crazyraccoons.mypet.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.crazyraccoons.mypet.data.database.PetItemDao;
import com.crazyraccoons.mypet.data.database.PetItemDatabase;
import com.crazyraccoons.mypet.data.model.PetItem;

import java.util.List;

public class PetItemRepository {

    private PetItemDao petItemDao;

    private LiveData<List<PetItem>> petItemList;

    public PetItemRepository(Application application) {
        PetItemDatabase database = PetItemDatabase.getInstance(application);
        petItemDao = database.petItemDao();
        petItemList = petItemDao.getAllProfiles();
    }

    public void insert(PetItem petItem) {
        new InsertPetItemAsyncTask(petItemDao).execute(petItem);
    }

    public LiveData<List<PetItem>> getAllProfiles() {
        return petItemList;
    }

    private static class InsertPetItemAsyncTask extends AsyncTask<PetItem, Void, Void> {
        private PetItemDao petItemDao;

        private InsertPetItemAsyncTask(PetItemDao petItemDao){
            this.petItemDao = petItemDao;
        }

        @Override
        protected Void doInBackground(PetItem... petItems){
            petItemDao.insert(petItems[0]);
            return null;
        }
    }

}
