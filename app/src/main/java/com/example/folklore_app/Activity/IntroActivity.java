package com.example.folklore_app.Activity;

import android.content.Intent;
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
    ActivityIntroBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityIntroBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        
        setVariable();

    }

    private void setVariable() {
        binding.loginBtn.setOnClickListener(v -> {
            if (mAuth.getCurrentUser()!=null){
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
            } else{
                startActivity(new Intent(IntroActivity.this, LoginActivity.class));
            }

        });
        binding.signupBtn.setOnClickListener(v -> startActivity(new Intent(IntroActivity.this, SignupActivity.class)));
    }
}