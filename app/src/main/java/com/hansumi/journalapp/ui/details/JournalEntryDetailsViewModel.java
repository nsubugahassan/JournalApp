package com.hansumi.journalapp.ui.details;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.hansumi.journalapp.data.JournalAppRepository;
import com.hansumi.journalapp.data.database.JournalEntry;

public class JournalEntryDetailsViewModel extends ViewModel {
    private LiveData<JournalEntry> mJournalEntryLiveData;
    private JournalAppRepository mJournalAppRepository;

    public JournalEntryDetailsViewModel(JournalAppRepository repository, int entryId) {
        mJournalAppRepository = repository;
        mJournalEntryLiveData = mJournalAppRepository.getJournalEntryById(entryId);
    }

    public LiveData<JournalEntry> getmJournalEntryLiveData() {
        return mJournalEntryLiveData;
    }
}
