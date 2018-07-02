package com.example.android.bakingapp.modules.recipes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RecipeAPI {
    private static final String baseURL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    private RecipeAPIInterface request() {
        return new Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeAPIInterface.class);
    }

    public Call<ArrayList<Recipe>> recipes() {
        return request().recipes();
    }
}

interface RecipeAPIInterface {
    @GET("baking.json")
    Call<ArrayList<Recipe>> recipes();
}
