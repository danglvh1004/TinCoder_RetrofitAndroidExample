package com.example.retrofitandroidexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Job job = new Job(1, "Coder");
        List<Favorite> favoriteList = new ArrayList<>();

        favoriteList.add(new Favorite(1, "Music"));
        favoriteList.add(new Favorite(2, "Read Book"));

        User user = new User(1, "HaiDang", true, job, favoriteList);

        Gson gson = new Gson();
        String strJson = gson.toJson(user);
        Log.e("String JSON", strJson);
    }
}