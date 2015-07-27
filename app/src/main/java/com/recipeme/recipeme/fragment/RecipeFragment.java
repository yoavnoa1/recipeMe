package com.recipeme.recipeme.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.common.collect.Lists;
import com.recipeme.recipeme.R;
import com.recipeme.recipeme.RecipeRowAdapter;
import com.recipeme.recipeme.entities.Ingredient;
import com.recipeme.recipeme.entities.Recipe;
import com.recipeme.recipeme.model.Model;

import java.util.Collection;
import java.util.List;

public class RecipeFragment extends Fragment
{
    private View view = null;
    private List<Ingredient> ingredients;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if(view == null)
        {
            view = inflater.inflate(R.layout.fragment_recipe, container, false);

            Collection<Recipe> Recipes = new Model().fetchRecipesBy(ingredients);
            List<Recipe> recipes = Lists.newArrayList(Recipes);

            RecipeRowAdapter rowAdapter = new RecipeRowAdapter(recipes, inflater);
            ListView list = (ListView) view.findViewById(R.id.recipe_listView);
            list.setAdapter(rowAdapter);
        }

        return view;
    }

    public void setIngredients(List<Ingredient> ingredients)
    {
        this.ingredients = ingredients;
    }
}
