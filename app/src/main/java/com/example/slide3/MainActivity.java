package com.example.slide3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
        RecyclerView recyclerView;
        photoAdapter adapter;
        ArrayList<photo> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rclphoto);
        LinearLayoutManager  linearLayoutManager = new LinearLayoutManager(this);
         recyclerView.setLayoutManager(linearLayoutManager);
        // Sử dụng Retrofit để gọi API và lấy dữ liệu
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        MyApi apiService = retrofit.create(MyApi.class);
        Call<List<photo>> call = apiService.getData();
        call.enqueue(new Callback<List<photo>>() {

            @Override
            public void onResponse(Call<List<photo>> call, Response<List<photo>> response) {

//            list = (ArrayList<photo>) response.body();
//            adapter = new photoAdapter(MainActivity.this  ,list);
//                Toast.makeText(MainActivity.this, list.size()+"ịofij", Toast.LENGTH_LONG).show();
//            recyclerView.setAdapter(adapter);
                if (response.isSuccessful()) {
                    list = (ArrayList<photo>) response.body();
                    Log.i("Sync","Sucess");
                    Log.i("Sync",String.valueOf(list.size()));
                    adapter = new photoAdapter(list);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.i("Sync","Fail");
                }

            }

            @Override
            public void onFailure(Call<List<photo>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}