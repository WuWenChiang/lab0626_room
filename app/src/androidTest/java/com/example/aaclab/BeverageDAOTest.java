package com.example.aaclab;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class BeverageDAOTest {
    BeverageDatabase database;

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
        //assertNull(database);
        assertNotNull(database);
    }
}
