package com.example.aaclab;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

@Database(entities = {BeverageEntity.class}, version = 1, exportSchema = false)
public abstract class BeverageDatabase extends RoomDatabase {
    private static final String TAG = BeverageDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE = "beverage_db";
    private static BeverageDatabase instance;
    public static BeverageDatabase getInstance(Context context) {
        synchronized (LOCK){
            if (instance==null) {
                Log.d(TAG,"create a new db instance");
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        BeverageDatabase.class, BeverageDatabase.DATABASE).build();

            }
        }
        Log.d(TAG, "get the db instance");
        return instance;
    }
    public abstract  BeverageDAO beverageDAO();
}
