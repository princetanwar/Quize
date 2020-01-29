package com.loveinshayari.quize;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.rv);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Categories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<CategoriModel> list = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        CategoriAdaptor adaptor = new CategoriAdaptor(list);
        recyclerView.setAdapter(adaptor);


        list.add(new CategoriModel("", "Categorie 1"));
        list.add(new CategoriModel("", "Categorie 2"));
        list.add(new CategoriModel("", "Categorie 3"));
        list.add(new CategoriModel("", "Categorie 4"));
        list.add(new CategoriModel("", "Categorie 5"));
        list.add(new CategoriModel("", "Categorie 5"));
        list.add(new CategoriModel("", "Categorie 5"));
        list.add(new CategoriModel("", "Categorie 5"));
        list.add(new CategoriModel("", "Categorie 5"));
        list.add(new CategoriModel("", "Categorie 5"));
        list.add(new CategoriModel("", "Categorie 5"));
    }
}
