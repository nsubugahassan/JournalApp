package com.hansumi.journalapp.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface JournalEntyDao {

    @Query("SELECT * FROM entries")
    LiveData<List<JournalEntry>> getAllJournalEntriesFromDb();

    @Query("SELECT * FROM entries WHERE date = :date")
    LiveData<List<JournalEntry>> getEntriesFromDbByDate(int date);

    @Query("SELECT * FROM entries WHERE entry_id = :entry_id")
    LiveData<JournalEntry> getJournalEntryByIdFromDb(int entry_id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addNewJournalEntry(JournalEntry journalEntry);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addNewJournalEntries(JournalEntry... journalEntry);

    @Delete
    void deleteJournalEntryFromDb(JournalEntry journalEntry);

    @Update
    void updateJournalEntryInDb(JournalEntry journalEntry);
}
