package com.example.folklore_app.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.folklore_app.Adapter.CartAdapter;
import com.example.folklore_app.Helper.ChangeNumberItemsListener;
import com.example.folklore_app.Helper.ManagmentCart;
import com.example.folklore_app.R;
import com.example.folklore_app.databinding.ActivityCartBinding;

public class CartActivity extends BaseActivity {
    private ActivityCartBinding binding;
    private RecyclerView.Adapter adapter;
    private ManagmentCart managmentCart;
    private double tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        managmentCart=new ManagmentCart(this);

        initList();

        setVariable();
        calculateCart();


    }

    private void initList() {
        if (managmentCart.getListCart().isEmpty()){
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scrollviewCart.setVisibility(View.GONE);
        }else {
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scrollviewCart.setVisibility(View.VISIBLE);
        }
        //LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        binding.cartView.setLayoutManager(new GridLayoutManager(CartActivity.this, 1));
        adapter=new CartAdapter(managmentCart.getListCart(), this, () -> calculateCart());
        binding.cartView.setAdapter(adapter);
    }

    private void calculateCart() {
        double percentTax=0.02; //percent20%
        double delivery = 10.0;
        tax= (Math.round(managmentCart.getTotalFee()*percentTax*100.0)/100);
        double total= (Math.round((managmentCart.getTotalFee()+tax+delivery)*100.0)/100);
        double itemTotal= (Math.round(managmentCart.getTotalFee()*100.0)/100);

        binding.totalFeeTxt.setText("$"+itemTotal);
        binding.taxTxt.setText("$"+tax);
        binding.deliveryTxt.setText("$"+delivery);
        binding.totalTxt.setText("$"+total);


    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());
    }


}