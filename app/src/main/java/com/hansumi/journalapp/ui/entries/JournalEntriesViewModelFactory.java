package com.hansumi.journalapp.ui.entries;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;

import com.hansumi.journalapp.data.JournalAppRepository;

public class JournalEntriesViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final JournalAppRepository mJournalAppRepository;

    public JournalEntriesViewModelFactory(JournalAppRepository repository) {
        mJournalAppRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new JournalEntriesViewModel(mJournalAppRepository);
    }
}
