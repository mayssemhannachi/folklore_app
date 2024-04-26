package com.example.folklore_app.Activity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.folklore_app.R;
import com.example.folklore_app.databinding.ActivityListFoodsBinding;

import java.util.ArrayList;

public class ListFoodsActivity extends BaseActivity {
ActivityListFoodsBinding binding;
private RecyclerView.Adapter adapterListFood;
private int categoryId;
private String categoryName;
private String searchText ;
private boolean isSearch ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityListFoodsBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        getIntentExtra();
        initList();



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initList() {
        DatabaseReferences myref =database.getReferences();
        binding.progressBar.setVisibility(view.VISIBLE);
        ArrayList<Foods> list=new ArrayList<>();
        Query query ;
        if(isSearch){
            query=myref.orderByChild("Title").startAt(searchText).endAt(searchText+\'uf8ff');
        }else{
            query=myref.orderByChild("CategoryId").equalTo(categoryId);

        }
    }

    private void getIntentExtra() {
        categoryId=getIntent().getIntExtra("CategoryId",0);
        categoryName=getIntent().getStringExtra("Category");
        searchText=getIntent().getStringExtra("text");
        isSearch=getIntent().getBooleanExtra("isSearch",false);
        binding.titleTxt.setText(categoryName);
        binding.backBtn.setOnClickListener(v -> finish());

    }
}