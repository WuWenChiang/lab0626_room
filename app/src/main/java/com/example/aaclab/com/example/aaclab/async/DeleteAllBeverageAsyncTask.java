package com.example.aaclab.com.example.aaclab.async;

import android.os.AsyncTask;

import com.example.aaclab.com.example.aaclab.db.BeverageDAO;

public class DeleteAllBeverageAsyncTask extends AsyncTask<Void, Void, Void> {
    private BeverageDAO beverageDAO;

    public DeleteAllBeverageAsyncTask(BeverageDAO beverageDAO) {
        this.beverageDAO = beverageDAO;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        beverageDAO.deleteAll();
        return null;
    }
}
