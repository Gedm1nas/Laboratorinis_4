package com.example.laboratorinis_4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListView listView;
    private ArrayList<String> notes;

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
        // Inicijuokite jūsų ListView adapterį
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Manually create menu items using the constants
        menu.add(Menu.NONE, CREATE_NOTE, Menu.NONE, "Sukurti užrašą");
        menu.add(Menu.NONE, DELETE_NOTE, Menu.NONE, "Ištrinti užrašą");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case CREATE_NOTE:
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
                return true;
            case DELETE_NOTE:
                startActivity(new Intent(MainActivity.this, DeleteNoteActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
