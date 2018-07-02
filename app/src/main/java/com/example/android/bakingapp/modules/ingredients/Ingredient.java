package com.example.android.bakingapp.modules.ingredients;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel
public class Ingredient {
    @SerializedName("ingredient")
    public final String name;
    public final double quantity;
    public final String measure;

    @ParcelConstructor
    public Ingredient(String name, double quantity, String measure) {
        this.name = name;
        this.quantity = quantity;
        this.measure = measure;
    }
}
