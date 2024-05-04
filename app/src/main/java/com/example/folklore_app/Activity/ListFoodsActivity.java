package com.example.folklore_app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.folklore_app.Adapter.FoodListAdapter;
import com.example.folklore_app.Domain.Foods;
import com.example.folklore_app.databinding.ActivityListFoodsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListFoodsActivity extends BaseActivity {
    ActivityListFoodsBinding binding;
    private RecyclerView.Adapter adapterListFood;
    private int categoryId;
    private String categoryName;
    private String searchText;
    private boolean isSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListFoodsBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        getIntentExtra();
        initList();
    }

    private ValueEventListener valueEventListener;


    private void initList() {
        DatabaseReference myRef = database.getReference("Foods");
        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<Foods> list = new ArrayList<>();

        Query query;
        if (isSearch) {
            query = myRef.orderByChild("Title").startAt(searchText).endAt(searchText + '\uf8ff');
            Log.d("ListFoodsActivity", "Search: " + searchText);
        } else {
            query = myRef.orderByChild("CategoryId").equalTo(categoryId);
        }

        if (valueEventListener != null) {
            query.removeEventListener(valueEventListener);
        }
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("ListFoodsActivity", "onDataChange called");
                if (snapshot.exists()) {
                    Log.d("ListFoodsActivity", "Snapshot exists");
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        Log.d("ListFoodsActivity", "Snapshot exists");

                        Foods food = issue.getValue(Foods.class);
                        Log.d("ListFoodsActivity", "Food: " + food);
                        if (food != null) {
                            food.setCategoryId(issue.child("CategoryId").getValue(Integer.class));
                        }
                        list.add(food);
                        Log.d("ListFoodsActivity", "Food: " + food.getTitle());
                    }
                    if (list.size() > 0) {
                        binding.foodListView.setLayoutManager(new GridLayoutManager(ListFoodsActivity.this, 2));
                        adapterListFood = new FoodListAdapter(list);
                        binding.foodListView.setAdapter(adapterListFood);
                        Log.d("ListFoodsActivity", "List size: " + list.size());

                    }
                    binding.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Gérer l'annulation de la requête ici
                Log.e("Firebase", error.getMessage());
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void getIntentExtra() {
        categoryId = getIntent().getIntExtra("CategoryId", 0);
        categoryName = getIntent().getStringExtra("CategoryName");
        searchText = getIntent().getStringExtra("text");
        isSearch = getIntent().getBooleanExtra("isSearch", false);
        Log.d("ListFoodsActivity", "CategoryId: " + categoryId + ", CategoryName: " + categoryName);
        binding.titleTxt.setText(categoryName);
        binding.backBtn.setOnClickListener(v -> finish());
    }
}
