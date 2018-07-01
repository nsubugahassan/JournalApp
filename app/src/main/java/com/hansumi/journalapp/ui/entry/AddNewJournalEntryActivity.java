package com.hansumi.journalapp.ui.entry;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.TypeConverters;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hansumi.journalapp.AppExecutors;
import com.hansumi.journalapp.R;
import com.hansumi.journalapp.data.JournalAppRepository;
import com.hansumi.journalapp.data.database.DateConverter;
import com.hansumi.journalapp.data.database.JournalEntry;
import com.hansumi.journalapp.ui.entries.JournalAppMainActivity;
import com.hansumi.journalapp.utilities.InjectorUtils;

import java.sql.SQLDataException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddNewJournalEntryActivity extends AppCompatActivity {

    private static final String LOG_TAG = AddNewJournalEntryActivity.class.getSimpleName();
    public static final String EDIT_ENTRY_ID = "edit_entry_id";
    EditText etTitle;
    EditText etBody;
    EditText etDate;
    Date mToday;

    // Constant for date format
    private static final String DATE_FORMAT = "dd/MM/yyy";
    // Date formatter
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_journal_entry);
        etTitle = findViewById(R.id.etTitle);
        etBody = findViewById(R.id.etBody);
        etDate = findViewById(R.id.etDate);
        mToday = new Date();
        String formartedDate = dateFormat.format(mToday);
        etDate.setText(formartedDate);

        Intent intent = getIntent();
        /**
         * ToDo finish editing functionality
         */
        if (intent.hasExtra(EDIT_ENTRY_ID) && intent.getIntExtra(EDIT_ENTRY_ID,0) > 0){

        }

    }

    public void saveUserJounalEntry(View v) {

        String title = etTitle.getText().toString();
        String body = etBody.getText().toString();
        final JournalEntry entry = getUserJournalEntry(title,body);

        if (null != entry){
            final JournalAppRepository repository = InjectorUtils.provideRepository(this);
            AppExecutors executors = AppExecutors.getInstance();
            executors.diskIO().execute(
                    () -> {
                        repository.addJournalEntry(entry);
                        Intent intent = new Intent(this, JournalAppMainActivity.class);
                        startActivity(intent);
                    }
            );
        }

    }

    private JournalEntry getUserJournalEntry(String title, String body){


        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)){

            return  new JournalEntry(title, mToday,body);

        }else {
            String message  = this.getResources().getString(R.string.fill_all_information);
            Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
            return null;
        }
    }




}
