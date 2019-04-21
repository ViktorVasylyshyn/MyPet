package com.crazyraccoons.mypet.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.crazyraccoons.mypet.data.model.PetItem;

import java.util.List;

@Dao
public interface PetItemDao {

    @Insert
    void insert(PetItem petItem);

    @Query("SELECT * FROM pet_table")
    LiveData<List<PetItem>> getAllProfiles();
}
