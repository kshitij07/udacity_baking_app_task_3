package com.example.android.bakingapp.modules.step;

import android.net.Uri;

public interface RecipeStepViewModelInterface {
    String getDescription();
    String getShortDescription();
    Uri getVideoUri();
    Uri getThumbnailUri();
    Boolean hasVideo();
    Boolean hasThumbnail();
    Uri getDefaultMediaPicture();
}
