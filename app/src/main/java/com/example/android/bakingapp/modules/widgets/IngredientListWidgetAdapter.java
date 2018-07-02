package com.example.android.bakingapp.modules.widgets;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.modules.ingredients.IngredientViewModelInterface;
import com.example.android.bakingapp.modules.recipes.RecipeViewModelInterface;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.ButterKnife;

public class IngredientListWidgetAdapter extends RemoteViewsService {
    ArrayList<IngredientViewModelInterface> ingredients;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new IngredientListViewsFactory(this.getApplicationContext());
    }

    class IngredientListViewsFactory implements RemoteViewsFactory {
        Context context;
        RecipeWidgetManager recipeWidgetManager;
        ArrayList<IngredientViewModelInterface> ingredients;


        public IngredientListViewsFactory(Context context) {
            this.context = context;
            recipeWidgetManager = new RecipeWidgetManager(context);
            RecipeViewModelInterface recipe = recipeWidgetManager.getRecipe();

            ingredients = new ArrayList<>();

            if (recipe != null) {
                ingredients = (ArrayList<IngredientViewModelInterface>) recipe.getIngredients();
            }
        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return ingredients.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_ingredient);

            views.setTextViewText(
                R.id.widgetIngredientNameTextView,
                ingredients.get(position).getIngredientDetail()
            );

            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}

