package com.example.android.bakingapp.modules.step;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.modules.common.ui.ItemOffsetDecoration;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepListFragment extends Fragment {
    public static final String BUNDLE_DATA_KEY = "RECIPE_STEPS_DATA";

    @BindView(R.id.recipeStepRecyclerView)
    RecyclerView recipeStepRecyclerView;

    ArrayList<RecipeStepViewModelInterface> steps;
    RecipeStepRecyclerViewAdapter adapter;
    RecipeStepRecyclerViewAdapter.OnClickHandler onRecipeStepClickHandler;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(BUNDLE_DATA_KEY, Parcels.wrap(steps));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipe_step_list_fragment, container, false);
        ButterKnife.bind(this, rootView);

        if (savedInstanceState != null) {
            steps = Parcels.unwrap(savedInstanceState.getParcelable(BUNDLE_DATA_KEY));
        }

        adapter = new RecipeStepRecyclerViewAdapter();
        adapter.setSteps(steps);
        adapter.setOnClickHandler(onRecipeStepClickHandler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(
            getContext(),
            LinearLayoutManager.VERTICAL,
            false
        );

        recipeStepRecyclerView.setLayoutManager(layoutManager);
        recipeStepRecyclerView.setAdapter(adapter);

        return rootView;
    }

    public void setSteps(ArrayList<RecipeStepViewModelInterface> steps) {
        this.steps = steps;
    }
    public void setOnRecipeStepClickHandler(RecipeStepRecyclerViewAdapter.OnClickHandler onRecipeStepClickHandler) {
        this.onRecipeStepClickHandler = onRecipeStepClickHandler;
    }
}
