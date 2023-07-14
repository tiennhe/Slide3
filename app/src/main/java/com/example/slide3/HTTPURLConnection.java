package com.example.slide3;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class HTTPURLConnection extends AsyncTask<String, Void, List<photo>> {


    private ArrayList<photo> list ;
    private Context context;



    @Override
    protected List<photo> doInBackground(String... strings) {
        try {
            URL url = new URL("");

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();


            reader.close();

            Gson gson = new Gson();
            photo[] myModelArray = gson.fromJson(response.toString(), photo[].class);
            return Arrays.asList(myModelArray);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();



    }

    @Override
    protected void onPostExecute(List<photo> photos) {
        super.onPostExecute(photos);
        if ( photos!= null) {
            list.clear();
            list.addAll(photos);

        }

    }
}
