package com.example.shivaycreativenotes;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivaycreativenotes.Activities.InsertNoteActivity;
import com.example.shivaycreativenotes.Activities.SplashActivity;
import com.example.shivaycreativenotes.Adapter.NotesRecyclerAdapter;
import com.example.shivaycreativenotes.Model.NotesModel;
import com.example.shivaycreativenotes.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    NotesViewModel viewModel;
    NotesRecyclerAdapter notesRecyclerAdapter;
    List<NotesModel> filteredAllList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TextView nofilterBTN, highToLowBtn, lowtoHighBTN;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.newNoteBTN);
        recyclerView = findViewById(R.id.mainNotesREcyclerView);
        viewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        nofilterBTN = findViewById(R.id.noFilterBTN);
        highToLowBtn = findViewById(R.id.highToLowBTN);
        lowtoHighBTN = findViewById(R.id.lowToHighBTN);

        int updateSnackbarValue = getIntent().getIntExtra("updateSnackBar", 0);
        int deleteNote = getIntent().getIntExtra("deleteNote", 0);
        int newNote = getIntent().getIntExtra("insertNote", 0);

        if (updateSnackbarValue == 99) {

            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Note has been Updated !", Snackbar.LENGTH_LONG);
            snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.green_new));
            snackbar.setTextColor(Color.BLACK);
            snackbar.setTextColor(Color.BLACK);
            snackbar.setDuration(1200);
            floatingActionButton.hide();
            snackbar.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    snackbar.dismiss();
                    floatingActionButton.show();
                }
            }, 1300);

        } else if (deleteNote == 999) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Note has been Deleted !", Snackbar.LENGTH_LONG);
            snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.red_new));
            snackbar.setTextColor(Color.WHITE);
            snackbar.setDuration(1200);
            floatingActionButton.hide();
            snackbar.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    snackbar.dismiss();
                    floatingActionButton.show();
                }
            }, 1300);

        } else if (newNote == 89) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "New Note Created !", Snackbar.LENGTH_LONG);
            snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.green_new));
            snackbar.setTextColor(Color.BLACK);
            snackbar.setDuration(1200);
            floatingActionButton.hide();
            snackbar.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    snackbar.dismiss();
                   floatingActionButton.show();
                }
            }, 1300);

        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InsertNoteActivity.class));
                finish();

            }
        });

        viewModel.getAllNotes.observe(this, new Observer<List<NotesModel>>() {
            @Override
            public void onChanged(List<NotesModel> notesModels) {
                setAdapter(notesModels);
                filteredAllList = notesModels;
            }
        });




        nofilterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadDATA(0);

                // SNACKBAR CODE <<<<<<<<<<<<<<<<<<
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Filter Removed !", Snackbar.LENGTH_LONG);
                snackbar.setBackgroundTint(ContextCompat.getColor(MainActivity.this, R.color.dark_grey));
                snackbar.setTextColor(Color.WHITE);

                snackbar.setDuration(1200);
                floatingActionButton.hide();
                snackbar.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        snackbar.dismiss();
                        floatingActionButton.show();
                    }
                }, 1300);
                // SNACKBAR CODE ENDS >>>>>>>>>>>>

                // CHANGE FONT CODE
                Typeface typefaceRegular = ResourcesCompat.getFont(MainActivity.this, R.font.sans_regular);
                Typeface typefaceBold = ResourcesCompat.getFont(MainActivity.this, R.font.sans_bold);
                nofilterBTN.setTypeface(typefaceBold);
                highToLowBtn.setTypeface(typefaceRegular);
                lowtoHighBTN.setTypeface(typefaceRegular);

                nofilterBTN.setTextColor(getResources().getColor(R.color.red_new));
                highToLowBtn.setTextColor(Color.GRAY);
                lowtoHighBTN.setTextColor(Color.GRAY);
                // ________________________________


            }
        });

        highToLowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadDATA(1);

                // SNACKBAR CODE <<<<<<<<<<<<<<<<<<
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Priority is High to Low", Snackbar.LENGTH_LONG);
                snackbar.setBackgroundTint(ContextCompat.getColor(MainActivity.this, R.color.red_new));
                snackbar.setTextColor(Color.WHITE);
                snackbar.setDuration(1200);
                floatingActionButton.hide();
                snackbar.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        snackbar.dismiss();
                        floatingActionButton.show();
                    }
                }, 1300);
                // SNACKBAR CODE ENDS >>>>>>>>>>>>

                // CHANGE FONT CODE
                Typeface typefaceRegular = ResourcesCompat.getFont(MainActivity.this, R.font.sans_regular);
                Typeface typefaceBold = ResourcesCompat.getFont(MainActivity.this, R.font.sans_bold);
                nofilterBTN.setTypeface(typefaceRegular);
                highToLowBtn.setTypeface(typefaceBold);
                lowtoHighBTN.setTypeface(typefaceRegular);

                highToLowBtn.setTextColor(getResources().getColor(R.color.red_new));
                nofilterBTN.setTextColor(Color.GRAY);
                lowtoHighBTN.setTextColor(Color.GRAY);
                // ________________________________

            }
        });

        lowtoHighBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadDATA(2);

                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Priority is Low to High", Snackbar.LENGTH_LONG);
                snackbar.setBackgroundTint(ContextCompat.getColor(MainActivity.this, R.color.green_new));
                snackbar.setTextColor(Color.BLACK);
                snackbar.setDuration(1200);
                floatingActionButton.hide();
                snackbar.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        snackbar.dismiss();
                        floatingActionButton.show();
                    }
                }, 1300);
                // SNACKBAR CODE ENDS >>>>>>>>>>>>

                // CHANGE FONT CODE
                Typeface typefaceRegular = ResourcesCompat.getFont(MainActivity.this, R.font.sans_regular);
                Typeface typefaceBold = ResourcesCompat.getFont(MainActivity.this, R.font.sans_bold);
                nofilterBTN.setTypeface(typefaceRegular);
                highToLowBtn.setTypeface(typefaceRegular);
                lowtoHighBTN.setTypeface(typefaceBold);
                lowtoHighBTN.setTextColor(getResources().getColor(R.color.red_new));
                highToLowBtn.setTextColor(Color.GRAY);
                nofilterBTN.setTextColor(Color.GRAY);

                // ________________________________

            }
        });
    }

    private void loadDATA(int i) {
        if (i == 0) {
            viewModel.getAllNotes.observe(this, new Observer<List<NotesModel>>() {
                @Override
                public void onChanged(List<NotesModel> notesModels) {
                    setAdapter(notesModels);
                    filteredAllList = notesModels;
                }
            });
        } else if (i == 1) {
            viewModel.highToLow.observe(this, new Observer<List<NotesModel>>() {
                @Override
                public void onChanged(List<NotesModel> notesModels) {
                    setAdapter(notesModels);
                    filteredAllList = notesModels;
                }
            });
        } else if (i == 2) {
            viewModel.lowTOHigh.observe(this, new Observer<List<NotesModel>>() {
                @Override
                public void onChanged(List<NotesModel> notesModels) {
                    setAdapter(notesModels);
                    filteredAllList = notesModels;
                }
            });
        }
    }

    public void setAdapter(List<NotesModel> notesModels) {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));

        notesRecyclerAdapter = new NotesRecyclerAdapter( MainActivity.this, notesModels);
        recyclerView.setAdapter(notesRecyclerAdapter);

        if (notesModels.size() == 0)  {
            Toast toast = Toast.makeText(this, "No Notes Here, TAP + to Add", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 300);
            toast.show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Notes Here...");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                NotesFilter(s);
                return false;
            }
        });
        return true;
    }

    private void NotesFilter(String s) {
        ArrayList<NotesModel> filtredNotesList = new ArrayList<>();

        for (NotesModel notesModel : this.filteredAllList) {
            if (notesModel.notesTitle.contains(s) || notesModel.notesSubtitle.contains(s) || notesModel.notes.contains(s)) {
                filtredNotesList.add(notesModel);
            }
        }

        this.notesRecyclerAdapter.searchNotes(filtredNotesList);

    }

    @Override
    public void onBackPressed() {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "EXIT THE APP", Snackbar.LENGTH_INDEFINITE)
                .setAction("YES", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                }).setActionTextColor(ContextCompat.getColor(MainActivity.this, R.color.yellow_new));
        snackbar.setBackgroundTint(ContextCompat.getColor(MainActivity.this, R.color.dark_grey));
        snackbar.setTextColor(Color.WHITE);
        floatingActionButton.hide();
        snackbar.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                snackbar.dismiss();
                floatingActionButton.show();
            }
        }, 3000);

    }

    public void hideFAB() {
        floatingActionButton.hide();
    }

    public void showUndoSnackbar(NotesModel undoNote) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), " Note Deleted, UNDO ?", Snackbar.LENGTH_INDEFINITE)
                .setAction("YES", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewModel.insertThisVNOTE(undoNote);
                    }
                }).setActionTextColor(ContextCompat.getColor(MainActivity.this, R.color.yellow_new));
        snackbar.setBackgroundTint(ContextCompat.getColor(MainActivity.this, R.color.dark_grey));
        snackbar.setTextColor(Color.WHITE);
        floatingActionButton.hide();
        snackbar.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                snackbar.dismiss();
                floatingActionButton.show();
            }
        }, 5000);

    }

}