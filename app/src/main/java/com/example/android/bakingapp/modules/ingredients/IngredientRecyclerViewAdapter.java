package com.example.android.bakingapp.modules.ingredients;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientRecyclerViewAdapter extends RecyclerView.Adapter<IngredientRecyclerViewAdapter.ViewHolder> {
    private ArrayList<IngredientViewModelInterface> ingredients;

    public IngredientRecyclerViewAdapter() {
        this.ingredients = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.ingredient, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setup(ingredients.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public void setIngredients(ArrayList<IngredientViewModelInterface> ingredients) {
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ingredientNameTextView)
        TextView ingredientNameTextView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setup(IngredientViewModelInterface viewModel) {
            ingredientNameTextView.setText(viewModel.getIngredientDetail());
        }
    }
}
