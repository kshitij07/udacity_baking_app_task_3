package com.example.android.bakingapp.modules.recipes;

import android.support.v4.app.LoaderManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.ViewHolder> {
    private ArrayList<RecipeViewModelInterface> recipes;
    private OnClickHandler onClickHandler;

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.recipe, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setup(position, recipes.get(position));
    }

    public void setRecipes(ArrayList<RecipeViewModelInterface> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    public void setOnClickHandler(OnClickHandler handler) {
        this.onClickHandler = handler;
    }

    public interface OnClickHandler {
        void onRecipeClicked(int position, RecipeViewModelInterface viewModel);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.recipeImageView)
        ImageView recipeImageView;

        @BindView(R.id.recipeNameTextView)
        TextView recipeNameTextView;

        @BindView(R.id.recipeStepCountTextView)
        TextView recipeStepCountTextView;

        int position;
        RecipeViewModelInterface viewModel;

        public ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        public void setup(int position, RecipeViewModelInterface viewModel) {
            this.viewModel = viewModel;
            this.position = position;

            recipeNameTextView.setText(viewModel.getName());
            recipeStepCountTextView.setText(viewModel.getRecipeCountText());

            if (viewModel.hasImage()) {
                recipeImageView.setVisibility(View.VISIBLE);

                Picasso
                    .with(this.itemView.getContext())
                    .load(viewModel.getImageUri())
                    .into(recipeImageView);
            }
        }

        @Override
        public void onClick(View view) {
            onClickHandler.onRecipeClicked(position, viewModel);
        }
    }
}
