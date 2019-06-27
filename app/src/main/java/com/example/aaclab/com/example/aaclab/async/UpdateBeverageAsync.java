package com.example.aaclab.com.example.aaclab.async;

import android.os.AsyncTask;

import com.example.aaclab.com.example.aaclab.db.BeverageDAO;
import com.example.aaclab.com.example.aaclab.db.BeverageEntity;

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