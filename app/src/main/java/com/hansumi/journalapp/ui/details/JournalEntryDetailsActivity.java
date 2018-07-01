package com.hansumi.journalapp.ui.details;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.hansumi.journalapp.R;
import com.hansumi.journalapp.data.database.JournalEntry;
import com.hansumi.journalapp.ui.entry.AddNewJournalEntryActivity;
import com.hansumi.journalapp.utilities.InjectorUtils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class JournalEntryDetailsActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvDate;
    TextView tvBody;
    private JournalEntryDetailsViewModel mJournalEntryDetailsViewModel;
    public static final String ENTRY_ID = "entry_id";
    private int mEntryId;
    private JournalEntry mJournalEntry;

    // Constant for date format
    private static final String DATE_FORMAT = "dd/MM/yyy";
    // Date formatter
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_entry_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(ENTRY_ID)){
            mEntryId = intent.getIntExtra(ENTRY_ID,0);
        }

        tvTitle = findViewById(R.id.tvTitleDetailsDisplay);
        tvDate = findViewById(R.id.tvDateDetailsDisplay);
        tvBody = findViewById(R.id.tvBodyDetailsDisplay);

        JournalEntryDetailsViewModelFactory factory = InjectorUtils
                .provideEntryDetailsViewModelFactory(this,mEntryId);
        mJournalEntryDetailsViewModel = ViewModelProviders.of(this, factory).get
                (JournalEntryDetailsViewModel.class);
        mJournalEntryDetailsViewModel.getmJournalEntryLiveData().observe(this, journalEntry -> {
            mJournalEntry = journalEntry;
            displayDetails(mJournalEntry);
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(JournalEntryDetailsActivity.this,
                       AddNewJournalEntryActivity.class);
               intent.putExtra(AddNewJournalEntryActivity.EDIT_ENTRY_ID,mJournalEntry.getEntryId() );
            }
        });
    }

    private void displayDetails(JournalEntry journalEntry){
        String date = dateFormat.format(journalEntry.getDate());
        tvDate.setText(date);
        tvTitle.setText(journalEntry.getTitle());
        tvBody.setText(journalEntry.getBody());

    }

}
