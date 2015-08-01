package com.recipeme.recipeme.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.recipeme.recipeme.R;
import com.recipeme.recipeme.entities.Recipe;

public class RecipePageFragment extends Fragment
{
    private View view = null;

    private Recipe recipe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (view == null)
        {
            view = inflater.inflate(R.layout.fragment_recipe_page, container, false);

            recipe = (Recipe) getArguments().get("Recipe");

            ((TextView) view.findViewById(R.id.recipe_title)).setText(recipe.getName());

            ((TextView) view.findViewById(R.id.recipe_ingredients)).setText(recipe.getIngredient());

            ((TextView) view.findViewById(R.id.recipe_prep)).setText(recipe.getPreparation());

            Bitmap bitmap = BitmapFactory.decodeByteArray(recipe.getPicture(), 0, recipe.getPicture().length);

            ((ImageView) view.findViewById(R.id.imageView)).setImageBitmap(bitmap);
        }

        return view;
    }
}
