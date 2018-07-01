/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hansumi.journalapp.utilities;

import android.content.Context;

import com.hansumi.journalapp.AppExecutors;
import com.hansumi.journalapp.data.JournalAppDatabase;
import com.hansumi.journalapp.data.JournalAppRepository;
import com.hansumi.journalapp.ui.details.JournalEntryDetailsViewModelFactory;
import com.hansumi.journalapp.ui.entries.JournalEntriesViewModelFactory;

/**
 * Provides static methods to inject the various classes needed for Sunshine
 */
public class InjectorUtils {

    public static JournalAppRepository provideRepository(Context context) {
        JournalAppDatabase database = JournalAppDatabase.getsInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();

        return JournalAppRepository.getInstance(database.journalEntyDao(), executors);
    }

    public static JournalEntriesViewModelFactory provideJournalEntriesViewModelFactory(Context context) {
        JournalAppRepository repository = provideRepository(context.getApplicationContext());
        return new JournalEntriesViewModelFactory(repository);
    }

    public static JournalEntryDetailsViewModelFactory provideEntryDetailsViewModelFactory(Context
     context, int entryId) {
        JournalAppRepository repository = provideRepository(context.getApplicationContext());
        return new JournalEntryDetailsViewModelFactory(repository, entryId);
    }

}