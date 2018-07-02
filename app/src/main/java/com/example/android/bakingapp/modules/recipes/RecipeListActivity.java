package com.example.android.bakingapp.modules.recipes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.bakingapp.R;

import butterknife.BindInt;
import butterknife.BindString;
import butterknife.ButterKnife;

public class RecipeListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list_activity);
    }
}
