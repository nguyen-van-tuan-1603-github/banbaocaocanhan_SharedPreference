package com.example.baicanhangiuaky;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextId, editTextEmail;
    private Button btnSave;
    private TextView textViewStatus;

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


        editTextName = findViewById(R.id.editTextName);
        editTextId = findViewById(R.id.editTextId);
        editTextEmail = findViewById(R.id.editTextEmail);
        btnSave = findViewById(R.id.btnSave);
        textViewStatus = findViewById(R.id.textViewStatus);


        loadSavedData();


        btnSave.setOnClickListener(v -> saveData());
    }

    private void saveData() {
        String name = editTextName.getText().toString().trim();
        String studentId = editTextId.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();

        if (name.isEmpty() || studentId.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }


        SharedPreferences sharedPreferences = getSharedPreferences("StudentInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name", name);
        editor.putString("studentId", studentId);
        editor.putString("email", email);
        editor.apply();

        Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();
        loadSavedData();
    }

    private void loadSavedData() {
        SharedPreferences sharedPreferences = getSharedPreferences("StudentInfo", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "N/A");
        String studentId = sharedPreferences.getString("studentId", "N/A");
        String email = sharedPreferences.getString("email", "N/A");

        textViewStatus.setText("Saved Info:\nName: " + name + "\nStudent ID: " + studentId + "\nEmail: " + email);
    }
}
