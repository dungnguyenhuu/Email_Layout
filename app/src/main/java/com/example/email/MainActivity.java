package com.example.email;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import io.bloco.faker.Faker;

public class MainActivity extends AppCompatActivity {

    List<EmailModel> emailModelList;
    EditText edtSearch;
    Button btnFavorite;
    EmailAdapter adapter;
    int d = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailModelList = new ArrayList<>();

       Faker faker = new Faker();

        for(int i = 0; i<15; i++){
            emailModelList.add(new EmailModel(faker.name.name(), faker.lorem.sentence(), faker.lorem.paragraph(), "12:00 PM"));
        }

        final RecyclerView recyclerView = findViewById(R.id.list_view_email);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new EmailAdapter(emailModelList);
        recyclerView.setAdapter(adapter);

        edtSearch = findViewById(R.id.etdSearch);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /*edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String temp = edtSearch.getText().toString();
                if(temp.equals("")){
                    adapter = new EmailAdapter(emailModelList);
                }else {
                    List<EmailModel> results = new ArrayList<>();

                    for (int i = 0; i < emailModelList.size(); i++) {
                        if (emailModelList.get(i).getName().indexOf(temp) != -1 ||
                                emailModelList.get(i).getSubject().indexOf(temp) != -1 ||
                                emailModelList.get(i).getContent().indexOf(temp) != -1) {
                            results.add(emailModelList.get(i));
                        }
                    }
                    adapter = new EmailAdapter(results);
                }
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/


        btnFavorite = findViewById(R.id.btnFavorite);
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                List<EmailModel> listFavorite = new ArrayList<>();
                for(int i = 0; i <emailModelList.size(); i++){
                    if(emailModelList.get(i).isFavorite()){
                        listFavorite.add(emailModelList.get(i));
                    }
                }
                if(d % 2 == 0){
                    adapter = new EmailAdapter(listFavorite);
                }else{
                    adapter = new EmailAdapter(emailModelList);
                }
                recyclerView.setAdapter(adapter);
                d++;
            }
        });

    }
}
