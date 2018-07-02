package com.example.android.bakingapp.modules.widgets;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.modules.recipes.RecipeViewModel;
import com.example.android.bakingapp.modules.recipes.RecipeViewModelInterface;
import com.google.gson.Gson;

public class RecipeWidgetManager {
    private Context context;
    private static final String PREFERENCE_NAME = "RecipeWidgetData";
    private static final String RECIPE_KEY = "Recipe";

    public RecipeWidgetManager(Context context) {
        this.context = context;
    }

    public void setRecipeToShow(RecipeViewModelInterface recipe) {
        String serializedRecipe = new Gson().toJson(recipe);

        sharedPreferences()
            .edit()
            .putString(RECIPE_KEY, serializedRecipe)
            .apply();

        updateWidget();
    }

    public RecipeViewModelInterface getRecipe() {
        String serializedRecipe = sharedPreferences().getString(RECIPE_KEY, null);

        if (serializedRecipe == null) {
            return null;
        }

        return new Gson().fromJson(serializedRecipe, RecipeViewModel.class);
    }

    public void updateWidget() {
        Intent intent = new Intent(context, RecipeWidgetProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName componentName = new ComponentName(context, RecipeWidgetProvider.class);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(componentName);

        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

        context.sendBroadcast(intent);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.ingredientListWidgetListView);
    }

    private SharedPreferences sharedPreferences() {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }
}
