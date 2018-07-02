package com.example.android.bakingapp.modules.recipes;

import android.net.Uri;
import android.text.TextUtils;

import com.example.android.bakingapp.modules.ingredients.Ingredient;
import com.example.android.bakingapp.modules.ingredients.IngredientViewModel;
import com.example.android.bakingapp.modules.ingredients.IngredientViewModelInterface;
import com.example.android.bakingapp.modules.step.RecipeStep;
import com.example.android.bakingapp.modules.step.RecipeStepViewModel;
import com.example.android.bakingapp.modules.step.RecipeStepViewModelInterface;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.ArrayList;

@Parcel
public class RecipeViewModel implements RecipeViewModelInterface {
    public Recipe recipe;
    public ArrayList<IngredientViewModel> ingredients;
    public ArrayList<RecipeStepViewModel> steps;

    @ParcelConstructor
    public RecipeViewModel(Recipe recipe) {
        this.recipe = recipe;

        this.ingredients = new ArrayList<>();
        this.steps = new ArrayList<>();

        for (Ingredient ingredient: recipe.ingredients) {
            this.ingredients.add(new IngredientViewModel(ingredient));
        }

        for (RecipeStep step: recipe.steps) {
            this.steps.add(new RecipeStepViewModel(step));
        }
    }

    public String getName() {
        return this.recipe.name;
    }

    public String getRecipeCountText() {
        return steps.size() + " Steps";
    }

    public Boolean hasImage() {
        return !TextUtils.isEmpty(recipe.imageURL);
    }

    public Uri getImageUri() {
        if (TextUtils.isEmpty(recipe.imageURL)) {
            return null;
        }

        return Uri.parse(recipe.imageURL);
    }

    public ArrayList<? extends IngredientViewModelInterface> getIngredients() {
        return this.ingredients;
    }

    public ArrayList<? extends RecipeStepViewModelInterface> getSteps() {
        return this.steps;
    }
}
