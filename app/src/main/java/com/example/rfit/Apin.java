package com.example.rfit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import com.example.rfit.Model;


public interface Apin {
    @POST("/users")


    Call<Model>  createPost(@Body Model model);
}
