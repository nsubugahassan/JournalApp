package com.hansumi.journalapp.ui.entries;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.hansumi.journalapp.data.JournalAppRepository;
import com.hansumi.journalapp.data.database.JournalEntry;

import java.util.List;

public class JournalEntriesViewModel extends ViewModel {

    private LiveData<List<JournalEntry>> mJournalEntries;
    private JournalAppRepository mJournalAppRepository;

    public JournalEntriesViewModel(JournalAppRepository repository) {
        mJournalAppRepository = repository;
        mJournalEntries = mJournalAppRepository.getAllJournalEntriesFromDb();
    }

    public LiveData<List<JournalEntry>> getMJournalEntries() {
        return mJournalEntries;
    }
}
