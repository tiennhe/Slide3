package com.example.slide3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApi {
    @GET("photos")
    Call<List<photo>> getData();


}
