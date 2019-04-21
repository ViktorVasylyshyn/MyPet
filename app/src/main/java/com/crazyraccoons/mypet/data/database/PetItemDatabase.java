package com.crazyraccoons.mypet.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.crazyraccoons.mypet.data.model.PetItem;

@Database(entities = {PetItem.class}, version = 1, exportSchema = false)
public abstract class PetItemDatabase extends RoomDatabase {

    private static PetItemDatabase instance;

    public abstract PetItemDao petItemDao();

    public static synchronized PetItemDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PetItemDatabase.class, "pet_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
