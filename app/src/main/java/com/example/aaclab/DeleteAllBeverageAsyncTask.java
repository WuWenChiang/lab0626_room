package com.example.aaclab;

import android.os.AsyncTask;

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
