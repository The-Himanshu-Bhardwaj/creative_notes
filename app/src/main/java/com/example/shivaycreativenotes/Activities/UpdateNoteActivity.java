package com.example.shivaycreativenotes.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.shivaycreativenotes.MainActivity;
import com.example.shivaycreativenotes.Model.NotesModel;
import com.example.shivaycreativenotes.R;
import com.example.shivaycreativenotes.ViewModel.NotesViewModel;
import com.example.shivaycreativenotes.databinding.ActivityInsertNoteBinding;
import com.example.shivaycreativenotes.databinding.ActivityUpdateNoteBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdateNoteActivity extends AppCompatActivity {

    ActivityUpdateNoteBinding binding;
    String priority = "1";
    String updatedTITLE, updatedSUBTITLE, updatedNOTE;
    NotesViewModel viewModel;

    String sTITLE, sSUBTITLE, sNOTE, sPRIORITY;
    int sID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // new learn it
        viewModel = ViewModelProviders.of(this).get(NotesViewModel.class);



        // FOR UPDATE ACTIVITY
        sTITLE = getIntent().getStringExtra("title");
        sSUBTITLE = getIntent().getStringExtra("subtitle");
        sNOTE = getIntent().getStringExtra("note");
        sID = getIntent().getIntExtra("id", 0);
        sPRIORITY = getIntent().getStringExtra("priority");
        //

        binding.updateTITLE.setText(sTITLE);
        binding.updateSUBTITLE.setText(sSUBTITLE);
        binding.updateNOTE.setText(sNOTE);

        if (sPRIORITY.equals("1")) {
            binding.greenPCIRCLE.setImageResource(R.drawable.done_blackk);
            binding.yellowPCIRCLE.setImageResource(0);
            binding.redPCRICLE.setImageResource(0);
        } else if (sPRIORITY.equals("2")) {
            binding.yellowPCIRCLE.setImageResource(R.drawable.done_blackk);
            binding.greenPCIRCLE.setImageResource(0);
            binding.redPCRICLE.setImageResource(0);
        } else if (sPRIORITY.equals("3")) {
            binding.redPCRICLE.setImageResource(R.drawable.done_blackk);
            binding.greenPCIRCLE.setImageResource(0);
            binding.yellowPCIRCLE.setImageResource(0);
        }

        binding.greenPCIRCLE.setOnClickListener(view -> {
            priority = "1";
            binding.greenPCIRCLE.setImageResource(R.drawable.done_blackk);
            binding.yellowPCIRCLE.setImageResource(0);
            binding.redPCRICLE.setImageResource(0);


        });

        binding.yellowPCIRCLE.setOnClickListener(view -> {
            priority = "2";
            binding.yellowPCIRCLE.setImageResource(R.drawable.done_blackk);
            binding.greenPCIRCLE.setImageResource(0);
            binding.redPCRICLE.setImageResource(0);

        });

        binding.redPCRICLE.setOnClickListener(view -> {
            priority = "3";
            binding.redPCRICLE.setImageResource(R.drawable.done_blackk);
            binding.greenPCIRCLE.setImageResource(0);
            binding.yellowPCIRCLE.setImageResource(0);

        });

        binding.updateNOTEFAB.setOnClickListener(view -> {
            updatedNOTE = binding.updateNOTE.getText().toString();
            updatedTITLE = binding.updateTITLE.getText().toString();
            updatedSUBTITLE = binding.updateSUBTITLE.getText().toString();


            UpdateNote(updatedTITLE, updatedSUBTITLE, updatedNOTE);
        });

    }

    private void UpdateNote(String updatedTITLE, String updatedSUBTITLE, String updatedNOTE) {


        NotesModel updatedNOTEmodel = new NotesModel();

        updatedNOTEmodel.id = sID;
        updatedNOTEmodel.notesTitle = updatedTITLE;
        updatedNOTEmodel.notesSubtitle = updatedSUBTITLE;
        updatedNOTEmodel.notes = updatedNOTE;

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM, d, yyyy");
        String noteDate = dateFormat.format(date);

        updatedNOTEmodel.notesDate = noteDate;
        updatedNOTEmodel.notesPriority = priority;

        viewModel.updateThisVNOTE(updatedNOTEmodel);


        Intent intent = new Intent(UpdateNoteActivity.this, MainActivity.class);
        intent.putExtra("updateSnackBar", 99);
        startActivity(intent);
        finish();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if  (item.getItemId() == R.id.ic_delete) {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(UpdateNoteActivity.this);
            View view = LayoutInflater.from(UpdateNoteActivity.this)
                    .inflate(R.layout.delete_bottom_sheet, (ConstraintLayout)findViewById(R.id.mainDelteLayout));

            bottomSheetDialog.setContentView(view);
            bottomSheetDialog.show();
            Button yes, no;

            yes = view.findViewById(R.id.confirm_delete);
            no = view.findViewById(R.id.cancel_delete);

            yes.setOnClickListener(view1 -> {

                viewModel.deleteThisVNOTE(sID);

                Intent intent = new Intent(UpdateNoteActivity.this, MainActivity.class);
                intent.putExtra("deleteNote", 999);
                startActivity(intent);
                finish();

            });

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bottomSheetDialog.dismiss();

                }
            });



        }
        return true;
    }
}