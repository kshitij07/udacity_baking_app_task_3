package com.example.android.bakingapp.modules.step;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.modules.recipes.Recipe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepRecyclerViewAdapter extends RecyclerView.Adapter<RecipeStepRecyclerViewAdapter.ViewHolder> {
    ArrayList<RecipeStepViewModelInterface> steps;
    OnClickHandler onClickHandler;

    public RecipeStepRecyclerViewAdapter() {
        this.steps = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recipe_step, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setup(position, steps.get(position));
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public void setSteps(ArrayList<RecipeStepViewModelInterface> steps) {
        this.steps = steps;
        notifyDataSetChanged();
    }

    public void setOnClickHandler(OnClickHandler onClickHandler) {
        this.onClickHandler = onClickHandler;
    }

    public interface OnClickHandler {
        public void onRecipeStepClicked(int position, RecipeStepViewModelInterface step);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.recipeStepShortDescription)
        TextView recipeStepShortDescriptionTextView;

        RecipeStepViewModelInterface step;
        int position;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(this);
        }

        public void setup(int position, RecipeStepViewModelInterface step) {
            this.step = step;
            this.position = position;

            // Position 0 is recipe introduction
            if (position == 0){
                recipeStepShortDescriptionTextView.setText(step.getShortDescription());
            } else {
                recipeStepShortDescriptionTextView.setText(String.format(
                    "%s. %s",
                    position,
                    step.getShortDescription()
                ));
            }
        }

        @Override
        public void onClick(View view) {
            onClickHandler.onRecipeStepClicked(position, step);
        }
    }
}
