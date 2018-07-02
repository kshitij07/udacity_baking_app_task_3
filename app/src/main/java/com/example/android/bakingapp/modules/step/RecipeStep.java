package com.example.android.bakingapp.modules.step;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel
public class RecipeStep {
    public final int id;
    public final String description;
    public final String shortDescription;
    public final String videoURL;
    public final String thumbnailURL;

    @ParcelConstructor
    public RecipeStep(
        int id,
        String description,
        String shortDescription,
        String videoURL,
        String thumbnailURL
    ) {
        this.id = id;
        this.description = description;
        this.shortDescription = shortDescription;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }
}
