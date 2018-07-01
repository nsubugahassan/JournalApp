package com.hansumi.journalapp.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.hansumi.journalapp.data.database.DateConverter;
import com.hansumi.journalapp.data.database.JournalEntry;
import com.hansumi.journalapp.data.database.JournalEntyDao;



@Database(entities = {JournalEntry.class},version = 3, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class JournalAppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "journal";

    //For Singleton instantiation
    private static final Object LOCK = new Object();
    private static volatile  JournalAppDatabase sInstance;

    public static JournalAppDatabase getsInstance(Context context){
        if (sInstance == null){
            synchronized (LOCK){
                if (sInstance == null){
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            JournalAppDatabase.class, JournalAppDatabase.DATABASE_NAME)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return sInstance;
    }

    public abstract JournalEntyDao journalEntyDao();

}
