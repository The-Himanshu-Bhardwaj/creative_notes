package com.example.shivaycreativenotes.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shivaycreativenotes.Model.NotesModel;
import com.example.shivaycreativenotes.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository notesRepository;
    public LiveData<List<NotesModel>> getAllNotes;
    public LiveData<List<NotesModel>> highToLow;
    public LiveData<List<NotesModel>> lowTOHigh;

    public NotesViewModel(@NonNull Application application) {
        super(application);

        // VIEW MODEL REPO SE DATA LETE HAI
        // ISLIYE REPO KA EK INSTANCE LENA HAI PEHLE
        //HERE >

        notesRepository = new NotesRepository(application);
        getAllNotes = notesRepository.getAllNotes;
        highToLow = notesRepository.highToLow;
        lowTOHigh = notesRepository.lowToHigh;


    }

    public void insertThisVNOTE(NotesModel note) {
        notesRepository.insertThisNote(note);
    }

    public void updateThisVNOTE(NotesModel note) {
        notesRepository.updateThisNote(note);
    }

    public void deleteThisVNOTE(int id) {
        notesRepository.deleteThisNote(id);
    }

}
