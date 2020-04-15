package com.example.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.bloco.faker.Faker;

public class MainActivity extends AppCompatActivity {

    List<EmailModel> emailModelList;
    //List<String> colorBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailModelList = new ArrayList<>();

        Faker faker = new Faker();

        for(int i = 0; i<10; i++){
            emailModelList.add(new EmailModel(faker.name.name(), faker.lorem.sentence(), faker.lorem.paragraph(), "12:00 PM"));
        }



        EmailAdapter adapter = new EmailAdapter(emailModelList);
        ListView listView = findViewById(R.id.list_view_email);
        listView.setAdapter(adapter);

    }
}
