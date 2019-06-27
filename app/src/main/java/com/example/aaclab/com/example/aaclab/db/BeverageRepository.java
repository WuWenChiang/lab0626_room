package com.example.aaclab.com.example.aaclab.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.example.aaclab.com.example.aaclab.async.DeleteAllBeverageAsyncTask;
import com.example.aaclab.com.example.aaclab.async.DeleteBeverageAsync;
import com.example.aaclab.com.example.aaclab.async.InsertAsyncTask;
import com.example.aaclab.com.example.aaclab.async.UpdateBeverageAsync;
import com.example.aaclab.com.example.aaclab.db.BeverageDAO;
import com.example.aaclab.com.example.aaclab.db.BeverageDatabase;
import com.example.aaclab.com.example.aaclab.db.BeverageEntity;

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

    public void updateBeverage(int id, String title, String detail) {
        BeverageEntity entity = new BeverageEntity(id, title, detail);
        new UpdateBeverageAsync(beverageDAO).execute(entity);
    }

    public void delete(BeverageEntity entity){
        new DeleteBeverageAsync(beverageDAO).execute(entity);
    }

    public void deleteAll() {
        new DeleteAllBeverageAsyncTask(beverageDAO).execute();
    }
}