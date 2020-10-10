package com.ahiralabata.ahirasqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText nama;
    Button store, show;
    TextView hasil;
    private DatabaseHelper databaseHelper;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        nama = findViewById(R.id.nama);
        store = findViewById(R.id.store);
        show = findViewById(R.id.showAll);
        hasil = findViewById(R.id.hasil);

        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.addName(nama.getText().toString());
                nama.setText("");
                Toast.makeText(MainActivity.this, "Stored Successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList = databaseHelper.getAllNames();
                hasil.setText("");
                for (int i = 0; i < arrayList.size(); i++){
                    hasil.setText(hasil.getText().toString()+", "+arrayList.get(i));
                }
            }
        });
    }
}