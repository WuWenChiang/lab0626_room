package com.example.aaclab;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity(tableName = "Beverage")
public class BeverageEntity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="id")
    private int id;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String detail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BeverageEntity(@NonNull int id, String title, String detail) {
        this.id = id;
        this.title = title;
        this.detail = detail;
    }
    @Ignore
    public BeverageEntity(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }
}