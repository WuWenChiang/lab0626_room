package com.example.aaclab;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

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
}