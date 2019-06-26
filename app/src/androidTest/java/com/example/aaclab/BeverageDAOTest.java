package com.example.aaclab;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class BeverageDAOTest {
    BeverageDatabase database;
    private static final String TAG = BeverageDAOTest.class.getSimpleName();

    @Before
    public void initDB() {
        database = Room.
                inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                        BeverageDatabase.class).build();
    }
    @After
    public void foo() {
        database.close();
    }
    @Test
    public void test1() {
        assertNotNull(database);
        Log.v(TAG, "test1, insert two records");
        database.beverageDAO().
                insertBeverage(new BeverageEntity(1, "americano", "shot coffee with hot water"));
        database.beverageDAO().
                insertBeverage(new BeverageEntity(2, "latte", "shot cofee with hot milk"));
        List<BeverageEntity> beverages = database.beverageDAO().loadAllBeverage();
        for (BeverageEntity e : beverages) {
            Log.v(TAG, String.format("get an entity:%s\n", e));
        }
        assertThat(beverages.size(),is(2));
        //assertNull(database);
    }

    @Test
    public void test2() {
        assertNotNull(database);
        Log.v(TAG, "test2, insert one record");
        database.beverageDAO().insertBeverage(new BeverageEntity("milk tea","earl grey with milk"));
        List<BeverageEntity> total = database.beverageDAO().loadAllBeverage();
        assertThat(total.size(),is(1));
    }
}
