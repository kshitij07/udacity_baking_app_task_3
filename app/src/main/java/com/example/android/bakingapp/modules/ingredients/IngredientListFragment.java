package com.example.android.bakingapp.modules.ingredients;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.R;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.RecyclerView.*;

public class IngredientListFragment extends Fragment {
    public static final String BUNDLE_DATA_KEY = "INGREDIENTS_DATA";

    @BindView(R.id.ingredientRecyclerView)
    RecyclerView ingredientRecyclerView;

    ArrayList<IngredientViewModelInterface> ingredients;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(BUNDLE_DATA_KEY, Parcels.wrap(ingredients));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            ingredients = Parcels.unwrap(savedInstanceState.getParcelable(BUNDLE_DATA_KEY));
        }

        View rootView = inflater.inflate(
            R.layout.ingredient_list_fragment,
            container,
            false
        );

        ButterKnife.bind(this, rootView);

        IngredientRecyclerViewAdapter adapter = new IngredientRecyclerViewAdapter();
        adapter.setIngredients(ingredients);
        LayoutManager layoutManager = new LinearLayoutManager(
            getContext(),
            LinearLayoutManager.VERTICAL,
            false
        );

        ingredientRecyclerView.setAdapter(adapter);
        ingredientRecyclerView.setLayoutManager(layoutManager);

        return rootView;
    }

    public void setIngredients(ArrayList<IngredientViewModelInterface> ingredients) {
        this.ingredients = ingredients;
    }
}
