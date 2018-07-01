package com.hansumi.journalapp.ui.entries;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hansumi.journalapp.R;
import com.hansumi.journalapp.data.database.JournalEntry;
import com.hansumi.journalapp.utilities.Utils;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class JournalEntriesAdapter extends RecyclerView.Adapter<JournalEntriesAdapter.JournalEntriesViewHoldeer>  {

    // Constant for date format
    private static final String DATE_FORMAT = "dd/MM/yyy";
    // Date formatter
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
    List<JournalEntry> mEntryListData;

    public JournalEntryItemClickHandler mJournalEntryItemClickHandler;



    public JournalEntriesAdapter(List<JournalEntry> allJournalEntires,
                                 JournalEntryItemClickHandler handler) {
        mEntryListData = allJournalEntires;
        mJournalEntryItemClickHandler = handler;
    }

    @NonNull
    @Override
    public JournalEntriesViewHoldeer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View journalEntryItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .item_journal_entry_layout,parent,false);
        return new JournalEntriesViewHoldeer(journalEntryItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull JournalEntriesViewHoldeer holder, int position) {
        JournalEntry entry = mEntryListData.get(position);
        holder.bind(entry);

    }

    @Override
    public int getItemCount() {
        if (mEntryListData != null && mEntryListData.size() > 0) {
            return mEntryListData.size();
        }

        return 0;
    }

    class JournalEntriesViewHoldeer extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvTitleDisplay;
        TextView tvDateDisplay;
        TextView tvBodyDisplay;

        public JournalEntriesViewHoldeer(View itemView) {
            super(itemView);
            tvTitleDisplay = itemView.findViewById(R.id.tvTitleDisplay);
            tvDateDisplay = itemView.findViewById(R.id.tvDateDisplay);
            tvBodyDisplay = itemView.findViewById(R.id.tvBodyDisplay);
        }

        private void bind(JournalEntry journalEntry){
            tvTitleDisplay.setText(journalEntry.getTitle());
            String date = dateFormat.format(journalEntry.getDate());
            tvDateDisplay.setText(date);
            String bodySummary = Utils.makeBriefTextFromDB(journalEntry.getBody());
            tvBodyDisplay.setText(bodySummary);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mJournalEntryItemClickHandler.onJournalEntryItemClicked(mEntryListData.get(position).getEntryId());
        }
    }

    public interface JournalEntryItemClickHandler{
        public void onJournalEntryItemClicked(int entryId);
    }
}
