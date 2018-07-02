package com.example.android.bakingapp.modules.ingredients;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel
public class IngredientViewModel implements IngredientViewModelInterface {
    public final Ingredient ingredient;

    @ParcelConstructor
    public IngredientViewModel(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String getIngredientDetail() {
        return String.format("%s %s of %s", ingredient.quantity, ingredient.measure, ingredient.name);
    }
}
