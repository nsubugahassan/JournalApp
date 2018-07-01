package com.hansumi.journalapp.ui.details;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;

import com.hansumi.journalapp.data.JournalAppRepository;
import com.hansumi.journalapp.data.database.JournalEntry;

public class JournalEntryDetailsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private int mEntryId;
    private JournalAppRepository mJournalAppRepository;
    public JournalEntryDetailsViewModelFactory(JournalAppRepository repository, int entryId) {
        mJournalAppRepository = repository;
        mEntryId = entryId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new JournalEntryDetailsViewModel(mJournalAppRepository, mEntryId );
    }
}