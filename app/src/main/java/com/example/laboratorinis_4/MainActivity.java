package com.example.laboratorinis_4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListView listView;
    private ArrayList<String> notes;
    private ArrayAdapter<String> adapter;

    // Define constants for menu items
    private static final int CREATE_NOTE = 1;
    private static final int DELETE_NOTE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);
        notes = new ArrayList<>();
        loadNotes();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, CREATE_NOTE, Menu.NONE, "Sukurti užrašą");
        menu.add(Menu.NONE, DELETE_NOTE, Menu.NONE, "Ištrinti užrašą");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case CREATE_NOTE:
                startActivityForResult(new Intent(MainActivity.this, AddNoteActivity.class), CREATE_NOTE);
                return true;
            case DELETE_NOTE:
                startActivityForResult(new Intent(MainActivity.this, DeleteNoteActivity.class), DELETE_NOTE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == CREATE_NOTE) {
                String note = data.getStringExtra("note");
                notes.add(note);
                saveNotes();
                adapter.notifyDataSetChanged();
            } else if (requestCode == DELETE_NOTE) {
                notes = data.getStringArrayListExtra("notes");
                saveNotes();
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void saveNotes() {
        SharedPreferences prefs = getSharedPreferences("notes", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Set<String> set = new HashSet<>(notes);
        editor.putStringSet("notes_set", set);
        editor.apply();
    }

    private void loadNotes() {
        SharedPreferences prefs = getSharedPreferences("notes", Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("notes_set", new HashSet<>());
        notes.addAll(set);
    }
}
