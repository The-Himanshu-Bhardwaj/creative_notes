package com.example.shivaycreativenotes.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.shivaycreativenotes.Model.NotesModel;

import java.util.List;

@Dao
public interface NotesDAO {

    @Query("SELECT * FROM Notes_Database")
    LiveData<List<NotesModel>> getAllNotes();

    @Query("SELECT * FROM Notes_Database ORDER BY notes_priority DESC")
    LiveData<List<NotesModel>> highToLow();

    @Query("SELECT * FROM Notes_Database ORDER BY notes_priority ASC")
    LiveData<List<NotesModel>> lowToHigh();

    @Insert
    void insertNotes(NotesModel... notes);

    @Query("DELETE FROM  Notes_Database WHERE id = :id ")
    void deleteNote(int id);

    @Update
    void updateNotes(NotesModel note);
}
