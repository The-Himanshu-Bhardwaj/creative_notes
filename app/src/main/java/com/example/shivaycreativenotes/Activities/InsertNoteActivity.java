package com.example.shivaycreativenotes.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.shivaycreativenotes.MainActivity;
import com.example.shivaycreativenotes.Model.NotesModel;
import com.example.shivaycreativenotes.R;
import com.example.shivaycreativenotes.ViewModel.NotesViewModel;
import com.example.shivaycreativenotes.databinding.ActivityInsertNoteBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InsertNoteActivity extends AppCompatActivity {

    String title, subTitle, note;
    ActivityInsertNoteBinding binding;
    NotesViewModel viewModel;
    String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        binding.doneFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = binding.addTitleTextBox.getText().toString();
                subTitle = binding.addSUBtitlebox.getText().toString();
                note = binding.addNOTEbox.getText().toString();

                CreateNote(title, subTitle, note);

            }
        });

        binding.greenPCIRCLE.setOnClickListener(view -> {
            priority = "1";
            binding.greenPCIRCLE.setImageResource(R.drawable.done_blackk);
            binding.yellowPCIRCLE.setImageResource(0);
            binding.redPCRICLE.setImageResource(0);


        });

        binding.yellowPCIRCLE.setOnClickListener(view -> {
            priority ="2";
            binding.yellowPCIRCLE.setImageResource(R.drawable.done_blackk);
            binding.greenPCIRCLE.setImageResource(0);
            binding.redPCRICLE.setImageResource(0);

        });

        binding.redPCRICLE.setOnClickListener(view -> {
            priority ="3";
            binding.redPCRICLE.setImageResource(R.drawable.done_blackk);
            binding.greenPCIRCLE.setImageResource(0);
            binding.yellowPCIRCLE.setImageResource(0);

        });


    }

    private void CreateNote(String title, String subTitle, String note) {

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM, d, yyyy");
        String noteDate = dateFormat.format(date);

        NotesModel newNote = new NotesModel();
        newNote.notesTitle = title;
        newNote.notesSubtitle = subTitle;
        newNote.notes = note;
        newNote.notesPriority = priority;
        newNote.notesDate = noteDate;

        viewModel.insertThisVNOTE(newNote);

        Intent intent = new Intent(InsertNoteActivity.this, MainActivity.class);
        intent.putExtra("insertNote", 89);
        startActivity(intent);

        finish();
    }
}