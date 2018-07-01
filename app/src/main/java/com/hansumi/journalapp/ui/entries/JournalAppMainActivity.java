package com.hansumi.journalapp.ui.entries;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.hansumi.journalapp.AppExecutors;
import com.hansumi.journalapp.R;
import com.hansumi.journalapp.data.JournalAppRepository;
import com.hansumi.journalapp.data.database.JournalEntry;
import com.hansumi.journalapp.ui.entry.AddNewJournalEntryActivity;
import com.hansumi.journalapp.utilities.InjectorUtils;

import java.util.List;

public class JournalAppMainActivity extends AppCompatActivity implements JournalEntriesAdapter.JournalEntryItemClickHandler{

    private static final String LOG_TAG = JournalAppMainActivity.class.getSimpleName();
    private RecyclerView mJournalEntriesRecyclerView;
    private JournalEntriesAdapter mJournalEntriesAdapter;
    private JournalEntriesViewModel mJournalEntriesViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_app_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mJournalEntriesRecyclerView = findViewById(R.id.journalEntriesRecyclerView);
        mJournalEntriesRecyclerView.setHasFixedSize(true);
        mJournalEntriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        JournalEntriesViewModelFactory factory = InjectorUtils
                .provideJournalEntriesViewModelFactory(this);

        mJournalEntriesViewModel = ViewModelProviders.of(this,factory).get(JournalEntriesViewModel.class);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startAddNewJournalActivityIntent = new Intent(JournalAppMainActivity.this,
                        AddNewJournalEntryActivity.class);
                startActivity(startAddNewJournalActivityIntent);
            }
        });

        getSavedEntries();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_journal_app_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void getSavedEntries(){
        mJournalEntriesViewModel.getMJournalEntries().observe(this, entries -> {
            if (entries != null && entries.size() > 0){
                mJournalEntriesAdapter = new JournalEntriesAdapter(entries,this);
                mJournalEntriesRecyclerView.setAdapter(mJournalEntriesAdapter);
                Log.d(LOG_TAG, "The database has many records: " + entries.size());
            }
        });

    }

    @Override
    public void onJournalEntryItemClicked(int entryId) {

    }
}
