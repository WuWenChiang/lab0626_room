package com.example.aaclab;

import android.os.AsyncTask;

public class UpdateBeverageAsync extends AsyncTask<BeverageEntity, Void, Void> {
    private BeverageDAO beverageDAO;

    public UpdateBeverageAsync(BeverageDAO beverageDAO) {
        this.beverageDAO = beverageDAO;
    }

    @Override
    protected Void doInBackground(BeverageEntity... beverageEntities) {
        beverageDAO.update(beverageEntities[0]);
        return null;
    }
}