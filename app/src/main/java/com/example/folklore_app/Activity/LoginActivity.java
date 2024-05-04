package com.example.folklore_app.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.folklore_app.Helper.DatabaseHelper;
import com.example.folklore_app.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {
    ActivityLoginBinding binding;
    DatabaseHelper databaseHelper; // Add this line


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this); // Initialize DatabaseHelper
        setVariable();


    }

    private void setVariable() {
    binding.login.setOnClickListener(v -> {
        String email = binding.userEdt.getText().toString();
        String password = binding.passEdt2.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, task -> {
            if (task.isSuccessful()) {
                databaseHelper.storeUser(email, password); // Store user's email and password
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else {
                // Firebase login failed, try SQLite login
                Cursor cursor = databaseHelper.getUser(email);
                if (cursor.moveToFirst()) {
                    int passwordColumnIndex = cursor.getColumnIndex("Password");
                    if (passwordColumnIndex != -1) {
                        String storedPassword = cursor.getString(passwordColumnIndex);
                        if (password.equals(storedPassword)) {
                            // SQLite login successful
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            // SQLite login failed
                            Toast.makeText(LoginActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Password column not found in SQLite database
                        Toast.makeText(LoginActivity.this, "Password column not found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // User not found in SQLite database
                    Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    });


        binding.signupBtn.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        });

        binding.forgotPassword.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
        });

    }
}
