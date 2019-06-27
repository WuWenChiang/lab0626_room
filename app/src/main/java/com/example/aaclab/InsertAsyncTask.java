package com.example.aaclab;

import android.os.AsyncTask;

public class InsertAsyncTask
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
