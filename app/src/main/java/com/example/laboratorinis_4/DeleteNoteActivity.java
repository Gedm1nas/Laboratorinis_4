package com.example.laboratorinis_4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DeleteNoteActivity extends AppCompatActivity {
    private static final String TAG = "DeleteNoteActivity";
    private Spinner spnNotes;
    private Button btnDelete;
    private ArrayList<String> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);
        Log.d(TAG, "onCreate: Activity created");

        spnNotes = findViewById(R.id.spnNotes);
        btnDelete = findViewById(R.id.btnDelete);

        notes = getIntent().getStringArrayListExtra("notes");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, notes);
        spnNotes.setAdapter(adapter);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedNote = spnNotes.getSelectedItem().toString();
                if (selectedNote != null) {
                    notes.remove(selectedNote);
                    Log.d(TAG, "onClick: Note deleted - " + selectedNote);
                    Toast.makeText(DeleteNoteActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putStringArrayListExtra("notes", notes);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
