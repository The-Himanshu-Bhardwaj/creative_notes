package com.example.shivaycreativenotes.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.shivaycreativenotes.DAO.NotesDAO;
import com.example.shivaycreativenotes.Database.NotesDatabase;
import com.example.shivaycreativenotes.Model.NotesModel;

import java.util.List;

// ** REPOSITORY DIRECTLY COMMUNICATES WITH THE DATABASE
// ** SO IT NEEDS AN INSTANCE OF THE DATABASE

public class NotesRepository {


    public NotesDAO notesDAO;
    public LiveData<List<NotesModel>> getAllNotes;
    public LiveData<List<NotesModel>> highToLow;
    public LiveData<List<NotesModel>> lowToHigh;


    public NotesRepository(Application application) {

        // ** REPOSITORY DIRECTLY COMMUNICATES WITH THE DATABASE
        // ** SO IT NEEDS AN INSTANCE OF THE DATABASE

        NotesDatabase database = NotesDatabase.getDatabaseInstance(application);
        notesDAO = database.notesDAO();
        getAllNotes = notesDAO.getAllNotes();
        highToLow = notesDAO.highToLow();
        lowToHigh = notesDAO.lowToHigh();
    }

    public void insertThisNote(NotesModel note) {
        notesDAO.insertNotes(note);
    }

    public void deleteThisNote(int id) {
        notesDAO.deleteNote(id);
    }

    public void updateThisNote(NotesModel note) {
        notesDAO.updateNotes(note);
    }

}
