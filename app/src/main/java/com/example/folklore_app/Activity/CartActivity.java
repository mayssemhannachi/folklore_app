package com.example.folklore_app.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.folklore_app.Helper.ManagmentCart;
import com.example.folklore_app.R;
import com.example.folklore_app.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {
    private ActivityCartBinding binding;
    private RecyclerView.Adapter adapter;
    private ManagmentCart managmentCart;
    private Double tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        managmentCart=new ManagmentCart(this);
        setVariable();
        calculateCart();
        initList();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loginBtn), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initList() {
    }

    private void calculateCart() {
        Double percentTax=0.02; //percent20%
        Double delivery = 10.0;
        tax= (double) (Math.round(managmentCart.getTotalFee()*percentTax*100.0)/100);
        Double total= (double) (Math.round((managmentCart.getTotalFee()+tax+delivery)*100.0)/100);
        Double itemTotal= (double) (Math.round(managmentCart.getTotalFee()*100.0)/100);

        binding.totalFeeTxt.setText("$"+itemTotal);
        binding.taxTxt.setText("$"+percentTax);
        binding.deliveryTxt.setText("$"+delivery);
        binding.totalTxt.setText("$"+total);


    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());
    }


}