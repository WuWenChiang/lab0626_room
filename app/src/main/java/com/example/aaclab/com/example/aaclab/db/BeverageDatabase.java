package com.example.aaclab.com.example.aaclab.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
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
                        BeverageDatabase.class, BeverageDatabase.DATABASE)
                                               .addCallback(sRoomDatabaseCallback)
                                               .build();
            }
        }
        Log.d(TAG, "get the db instance");
        return instance;
    }
    public abstract  BeverageDAO beverageDAO();

    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void> {
        private final BeverageDAO dao;
        String[] titles = {"black tea", "oolong", "macha", "americano", "latte", "americano", "latte"};
        String[] details = {"hot", "no sugar", "hot", "ice", "ice", "hot", "hot"};

        PopulateDBAsync(BeverageDatabase db) {
            dao = db.beverageDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < titles.length; i++) {
                dao.insertBeverage(new BeverageEntity(titles[i], details[i]));
            }
            return null;
        }
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsync(instance).execute();
        }
    };
}
