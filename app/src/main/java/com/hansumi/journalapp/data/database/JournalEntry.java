package com.hansumi.journalapp.data.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "entries", indices = {@Index(value = {"title","date"},unique = true)})
public class JournalEntry {

    @ColumnInfo(name = "entry_id")
    @PrimaryKey(autoGenerate = true)
    private int entryId;

    private String  title;
    private Date date;
    private String body;


    public JournalEntry(int entryId, String title, Date date, String body) {
        this.entryId = entryId;
        this.title = title;
        this.date = date;
        this.body = body;
    }

    @Ignore
    public JournalEntry(String title, Date date, String body) {
        this.entryId = entryId;
        this.title = title;
        this.date = date;
        this.body = body;
    }

    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
