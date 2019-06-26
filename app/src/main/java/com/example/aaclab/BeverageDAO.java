package com.example.aaclab;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface BeverageDAO {
    @Query("SELECT * FROM Beverage ORDER BY id")
    List<BeverageEntity> loadAllBeverage();

    @Insert
    void insertBeverage(BeverageEntity beverageEntity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateBeverage(BeverageEntity beverageEntity);

    @Delete
    void delete(BeverageEntity beverageEntity);

    @Query("SELECT * FROM Beverage WHERE id = :id")
    BeverageEntity getBeverageEntityById(int id);
}