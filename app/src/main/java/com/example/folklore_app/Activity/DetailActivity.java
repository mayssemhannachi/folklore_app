package com.example.folklore_app.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.folklore_app.Domain.Foods;
import com.example.folklore_app.Helper.ManagmentCart;
import com.example.folklore_app.R;
import com.example.folklore_app.databinding.ActivityDetailBinding;
import com.example.folklore_app.databinding.ActivityMainBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    private Foods object;
    private int num=0;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();

    }

    private void setVariable() {
        managmentCart=new ManagmentCart(this);
        binding.backBtn.setOnClickListener(v -> finish());

        Glide.with(DetailActivity.this)
                .load(object.getImagePath())
                .into(binding.pic);

        binding.priceTxt.setText("$"+object.getPrice());
        binding.titleTxt.setText(object.getTitle());
        binding.descriptionTxt.setText(object.getDescription());
        binding.rateTxt.setText(object.getStar()+"");
        binding.timeTxt.setText((float)object.getTimeValue()+" min");
        binding.totalTxt.setText(num +object.getPrice()+"$");

        binding.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num=num+1;
                binding.qtTxt.setText(num+" ");
                binding.priceTxt.setText(("$"+(num*object.getPrice())));

            }
        });

        binding.minusBtn.setOnClickListener(v -> {
            if (num >1){
                num=num-1;
                binding.qtTxt.setText(num+" ");
                binding.totalTxt.setText("$"+(num*object.getPrice()));
            }
        });

        binding.addBtn.setOnClickListener(v -> {
            object.setNumberInCart(num);
            managmentCart.insertFood(object);

        });
    }

    private void getIntentExtra() {
        object= (Foods) getIntent().getSerializableExtra("object");
    }
}