package com.example.folklore_app.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.folklore_app.R;
import com.example.folklore_app.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity {


    //direct references to all views that have an ID in the corresponding XML layout files.//
    ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       //This method is used to set the content view of the activity.//
        binding = ActivityIntroBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

            
        setVariable();

        getWindow().setStatusBarColor(Color.parseColor("#FFE45B"));
        
    }

    private void setVariable() {

        //This method is used to set the content view of the activity.//
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}