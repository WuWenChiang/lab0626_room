package com.example.aaclab;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.aaclab.com.example.aaclab.db.BeverageEntity;
import com.example.aaclab.com.example.aaclab.db.BeverageRepository;

import java.util.List;

public class BeverageViewModel extends AndroidViewModel {
    private BeverageRepository repository;
    private LiveData<List<BeverageEntity>> allBeverage;

    public BeverageViewModel(@NonNull Application application) {
        super(application);
        repository = new BeverageRepository(application);
        allBeverage = repository.getAllBeverage();
    }
    public LiveData<List<BeverageEntity>> getAllBeverages() {
        return allBeverage;
    }
    public void insert(BeverageEntity entity){
        repository.insert(entity);
    }
    public void updateEntity(int id, String title, String detail){
        repository.updateBeverage(id, title, detail);
    }
    public void delete(BeverageEntity entity){
        repository.delete(entity);
    }
    public void deleteAll() { repository.deleteAll(); }
}