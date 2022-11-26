package com.example.shivaycreativenotes.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.shivaycreativenotes.Model.NotesModel;
import com.example.shivaycreativenotes.DAO.NotesDAO;

@Database(entities = {NotesModel.class}, version = 1)

public abstract class NotesDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "notesDatabase.db";

    public abstract NotesDAO notesDAO();

    public static NotesDatabase INSTANCE;

    public static NotesDatabase getDatabaseInstance(Context context)
    {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NotesDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        }
            return INSTANCE;
    }
}
