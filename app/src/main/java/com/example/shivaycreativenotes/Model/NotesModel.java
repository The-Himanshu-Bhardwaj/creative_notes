package com.example.shivaycreativenotes.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes_Database")
public class NotesModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "notes_Title")
    public String notesTitle;
    @ColumnInfo(name = "notes_SubTitle")
    public String notesSubtitle;
    @ColumnInfo(name = "notes_Date")
    public String notesDate;
    @ColumnInfo(name = "notes")
    public String notes;
    @ColumnInfo(name = "notes_priority")
    public String notesPriority;


}
