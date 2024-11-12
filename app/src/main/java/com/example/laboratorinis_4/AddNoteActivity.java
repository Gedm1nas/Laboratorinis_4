package com.example.laboratorinis_4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {
    private static final String TAG = "AddNoteActivity";
    private EditText edtNoteName;
    private EditText edtNoteContent;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_add_note);
        Log.d(TAG, "onCreate: Activity created");

        edtNoteName = findViewById(R.id.edtNoteName);
        edtNoteContent = findViewById(R.id.edtNoteContent);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtNoteName.getText().toString();
                String content = edtNoteContent.getText().toString();

                if (name.isEmpty() || content.isEmpty()) {
                    Toast.makeText(AddNoteActivity.this, "Both fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG, "onClick: Note saved - Name: " + name + ", Content: " + content);
                    Intent intent = new Intent();
                    intent.putExtra("note", name + ": " + content);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
