package com.example.android.bakingapp.modules.recipes;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.modules.ingredients.IngredientListFragment;
import com.example.android.bakingapp.modules.ingredients.IngredientViewModelInterface;
import com.example.android.bakingapp.modules.step.RecipeStepDetailActivity;
import com.example.android.bakingapp.modules.step.RecipeStepDetailFragment;
import com.example.android.bakingapp.modules.step.RecipeStepListFragment;
import com.example.android.bakingapp.modules.step.RecipeStepRecyclerViewAdapter;
import com.example.android.bakingapp.modules.step.RecipeStepViewModelInterface;
import com.example.android.bakingapp.modules.widgets.RecipeWidgetManager;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity
    implements RecipeStepRecyclerViewAdapter.OnClickHandler {
    static final String CURRENT_INDEX_BUNDLE_KEY = "RECIPE_CURRENT_INDEX";
    static final String RECIPES_BUNDLE_KEY = "RECIPES";

    RecipeStepDetailFragment recipeStepDetailFragment;
    ArrayList<RecipeViewModelInterface> recipes;
    ArrayList<RecipeStepViewModelInterface> steps;

    @BindView(R.id.toPreviousRecipeButton)
    Button toPreviousRecipeButton;

    @BindView(R.id.toNextRecipeButton)
    Button toNextRecipeButton;

    @BindView(R.id.recipeDetailScrollView)
    ScrollView recipeDetailScrollView;

    int currentIndex;
    RecipeWidgetManager recipeWidgetManager;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(RECIPES_BUNDLE_KEY, Parcels.wrap(recipes));
        outState.putInt(CURRENT_INDEX_BUNDLE_KEY, currentIndex);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recipeWidgetManager = new RecipeWidgetManager(this);
        setContentView(R.layout.recipe_detail);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(CURRENT_INDEX_BUNDLE_KEY);
            recipes = Parcels.unwrap(savedInstanceState.getParcelable(RECIPES_BUNDLE_KEY));
        } else {
            Intent intent = getIntent();
            currentIndex = intent.getIntExtra(Intent.EXTRA_INDEX, 0);
            recipes = Parcels.unwrap(intent.getParcelableExtra(Intent.EXTRA_INTENT));
        }

        RecipeViewModelInterface recipeViewModel = recipes.get(currentIndex);

        setup(recipeViewModel);

        toPreviousRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = Math.max(0, currentIndex - 1);
                RecipeViewModelInterface recipeViewModel = recipes.get(currentIndex);
                setup(recipeViewModel);
            }
        });

        toNextRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = Math.min(recipes.size() - 1, currentIndex + 1);

                RecipeViewModelInterface recipeViewModel = recipes.get(currentIndex);
                setup(recipeViewModel);
            }
        });
    }

    private void setup(RecipeViewModelInterface recipeViewModel) {
        recipeDetailScrollView.scrollTo(0, 0);

        if (currentIndex > 0) {
            toPreviousRecipeButton.setText(recipes.get(currentIndex - 1).getName());
            toPreviousRecipeButton.setVisibility(View.VISIBLE);
        } else {
            toPreviousRecipeButton.setVisibility(View.INVISIBLE);
        }

        if (currentIndex < (recipes.size() - 1)) {
            toNextRecipeButton.setText(recipes.get(currentIndex + 1).getName());
            toNextRecipeButton.setVisibility(View.VISIBLE);
        } else {
            toNextRecipeButton.setVisibility(View.INVISIBLE);
        }

        FragmentManager fragmentManager = getFragmentManager();

        setTitle(recipeViewModel.getName());

        // Setup ingredient list fragment
        ArrayList<IngredientViewModelInterface> ingredients = (ArrayList<IngredientViewModelInterface>) recipeViewModel.getIngredients();

        IngredientListFragment ingredientListFragment = new IngredientListFragment();

        getFragmentManager()
            .beginTransaction()
            .replace(R.id.ingredientListFragmentContainer, ingredientListFragment, IngredientListFragment.class.toString())
            .commit();

        ingredientListFragment.setIngredients(ingredients);

        // Setup recipe step list fragment
        steps = (ArrayList<RecipeStepViewModelInterface>) recipeViewModel.getSteps();
        RecipeStepListFragment recipeStepListFragment = new RecipeStepListFragment();
        recipeStepListFragment.setOnRecipeStepClickHandler(this);

        getFragmentManager()
            .beginTransaction()
            .replace(R.id.recipeStepListFragmentContainer, recipeStepListFragment)
            .commit();

        recipeStepListFragment.setSteps(steps);

        if (findViewById(R.id.recipeStepDetailFragmentContainer)  != null) {
            setupRecipeStepDetailFragment(steps.get(0));
        }
    }

    private void setupRecipeStepDetailFragment(RecipeStepViewModelInterface step) {
        recipeStepDetailFragment = new RecipeStepDetailFragment();
        recipeStepDetailFragment.setRecipeStep(step);

        getFragmentManager()
            .beginTransaction()
            .replace(R.id.recipeStepDetailFragmentContainer, recipeStepDetailFragment)
            .commit();
    }

    @Override
    public void onRecipeStepClicked(int position, RecipeStepViewModelInterface step) {
        if (recipeStepDetailFragment != null) {
            setupRecipeStepDetailFragment(step);
        } else {
            Intent intent = new Intent(this, RecipeStepDetailActivity.class);
            intent.putExtra(Intent.EXTRA_INTENT, Parcels.wrap(steps));
            intent.putExtra(Intent.EXTRA_INDEX, position);

            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recipe_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.pinRecipeToWidget) {
            recipeWidgetManager.setRecipeToShow(recipes.get(currentIndex));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
