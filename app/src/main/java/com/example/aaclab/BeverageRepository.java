package com.example.aaclab;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class BeverageRepository {
    private BeverageDAO beverageDAO;
    private LiveData<List<BeverageEntity>> allBeverage;

    public BeverageRepository(Application application) {
        BeverageDatabase db = BeverageDatabase.getInstance(application);
        beverageDAO = db.beverageDAO();
        allBeverage = beverageDAO.loadAllBeverage();
    }

    public LiveData<List<BeverageEntity>> getAllBeverage() {
        return allBeverage;
    }

    public void insert(BeverageEntity entity) {
        new InsertAsyncTask(beverageDAO).execute(entity);
    }

    private static class InsertAsyncTask
            extends AsyncTask<BeverageEntity, Void, Void> {
        private BeverageDAO beverageDAO;

        public InsertAsyncTask(BeverageDAO beverageDAO) {
            this.beverageDAO = beverageDAO;
        }

        @Override
        protected Void doInBackground(BeverageEntity... beverageEntities) {
            beverageDAO.insertBeverage(beverageEntities[0]);
            return null;
        }
    }
}