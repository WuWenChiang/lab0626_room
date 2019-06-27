package com.example.aaclab;

import android.os.AsyncTask;

public class DeleteBeverageAsync extends AsyncTask<BeverageEntity, Void, Void> {
    private BeverageDAO beverageDAO;

    public DeleteBeverageAsync(BeverageDAO beverageDAO) {
        this.beverageDAO = beverageDAO;
    }

    @Override
    protected Void doInBackground(BeverageEntity... beverageEntities) {
        beverageDAO.delete(beverageEntities[0]);
        return null;
    }
}