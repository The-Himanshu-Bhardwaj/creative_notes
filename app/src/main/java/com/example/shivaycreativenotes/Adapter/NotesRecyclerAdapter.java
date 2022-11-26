package com.example.shivaycreativenotes.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shivaycreativenotes.Activities.UpdateNoteActivity;
import com.example.shivaycreativenotes.MainActivity;
import com.example.shivaycreativenotes.Model.NotesModel;
import com.example.shivaycreativenotes.R;
import com.example.shivaycreativenotes.ViewModel.NotesViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder> {
    MainActivity mainActivity;
    List<NotesModel> notesModelList;
    List<NotesModel> allNOTES;
    NotesViewModel viewModel;


    public NotesRecyclerAdapter(MainActivity mainActivity, List<NotesModel> notesModelList) {
        this.mainActivity = mainActivity;
        this.notesModelList = notesModelList;
        allNOTES = new ArrayList<>(notesModelList);
        viewModel = ViewModelProviders.of(mainActivity).get(NotesViewModel.class);

    }

    public void searchNotes(List<NotesModel> filtredName) {
        this.notesModelList = filtredName;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.notes_item_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotesModel model = notesModelList.get(position);
        holder.titleTV.setText(model.notesTitle);
        holder.subTitleTV.setText(model.notesSubtitle);
        holder.noteTv.setText(model.notes);
        holder.dateTV.setText(model.notesDate);

        if (model.notesPriority.equals("1")) {
            holder.notesPriority.setBackgroundResource(R.drawable.grreen_circle);
        } else if (model.notesPriority.equals("2")) {
            holder.notesPriority.setBackgroundResource(R.drawable.yellow_circle);
        } else holder.notesPriority.setBackgroundResource(R.drawable.red_circle);

        holder.itemView.setOnClickListener(view -> {

            Intent intent = new Intent(mainActivity, UpdateNoteActivity.class);

            intent.putExtra("id", model.id);
            intent.putExtra("title", model.notesTitle);
            intent.putExtra("subtitle", model.notesSubtitle);
            intent.putExtra("note", model.notes);
            intent.putExtra("priority", model.notesPriority);

            mainActivity.startActivity(intent);
            mainActivity.finish();
        });

        // LONG CLICK LISTENER

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                viewModel.deleteThisVNOTE(model.id);

                NotesModel undoNote = new NotesModel();

                undoNote.notesTitle = model.notesTitle;
                undoNote.notesSubtitle = model.notesSubtitle;
                undoNote.notes = model.notes;
                undoNote.id = model.id;
                undoNote.notesDate = model.notesDate;
                undoNote.notesPriority = model.notesPriority;

                mainActivity.showUndoSnackbar(undoNote);

                return true;

            }
        });

        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    }


    @Override
    public int getItemCount() {
        return notesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTV, subTitleTV, noteTv, dateTV;
        View notesPriority;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.titleTV);
            subTitleTV = itemView.findViewById(R.id.subtitleTV);
            noteTv = itemView.findViewById(R.id.noteTV);
            dateTV = itemView.findViewById(R.id.dateTV);
            notesPriority = itemView.findViewById(R.id.priorityViewDOT);


        }
    }
}
