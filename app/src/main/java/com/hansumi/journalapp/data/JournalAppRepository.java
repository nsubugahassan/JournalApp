package com.hansumi.journalapp.data;

import android.arch.lifecycle.LiveData;

import com.hansumi.journalapp.AppExecutors;
import com.hansumi.journalapp.data.database.JournalEntry;
import com.hansumi.journalapp.data.database.JournalEntyDao;

import java.util.List;

public class JournalAppRepository  {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static JournalAppRepository sInstance;
    private final JournalEntyDao mJournalEntyDao;
    private final AppExecutors mExecutors;
    private boolean mInitialized = false;

    private JournalAppRepository(JournalEntyDao journalEntyDao, AppExecutors appExecutors) {
        mJournalEntyDao = journalEntyDao;
        mExecutors = appExecutors;
    }

    public synchronized static JournalAppRepository getInstance(JournalEntyDao journalEntyDao,
                                                                AppExecutors appExecutors){
        if (sInstance == null){
            synchronized (LOCK){
                sInstance = new JournalAppRepository(journalEntyDao, appExecutors);
            }
        }

        return sInstance;
    }


    public void addJournalEntry(JournalEntry journalEntry){
        mJournalEntyDao.addNewJournalEntry(journalEntry);
    }

    public LiveData<List<JournalEntry>> getAllJournalEntriesFromDb(){

        return mJournalEntyDao.getAllJournalEntriesFromDb();
    }

    public LiveData<JournalEntry> getJournalEntryById(int entryId){
        return mJournalEntyDao.getJournalEntryByIdFromDb(entryId);
    }

    public void updateJournalEntry(JournalEntry journalEntry){
        mJournalEntyDao.updateJournalEntryInDb(journalEntry);
    }

    public void deleteJournalEntry(JournalEntry journalEntry){
        mJournalEntyDao.deleteJournalEntryFromDb(journalEntry);
    }
}

