package com.example.android.bakingapp.modules.recipes;

import android.net.Uri;

import com.example.android.bakingapp.modules.ingredients.IngredientViewModelInterface;
import com.example.android.bakingapp.modules.step.RecipeStepViewModelInterface;

import java.util.ArrayList;

public interface RecipeViewModelInterface {
    String getName();
    String getRecipeCountText();
    Boolean hasImage();
    Uri getImageUri();
    ArrayList<? extends IngredientViewModelInterface> getIngredients();
    ArrayList<? extends RecipeStepViewModelInterface> getSteps();
}
