package com.recipeme.recipeme;

import android.widget.ArrayAdapter;
import android.widget.Filter;

import com.google.common.collect.Lists;
import com.recipeme.recipeme.entities.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeFilter extends Filter
{
    private final List<Recipe> recipes;
    private final List<Recipe> recipesCopy = Lists.newArrayList();
    private final ArrayAdapter<Recipe> recipeArrayAdapter;

    public RecipeFilter(List<Recipe> recipes, ArrayAdapter<Recipe> recipeArrayAdapter)
    {
        this.recipes = recipes;
        this.recipeArrayAdapter = recipeArrayAdapter;
        recipesCopy.addAll(recipes);
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint)
    {
        FilterResults filterResults = new FilterResults();
        ArrayList<Recipe> tempList = new ArrayList<Recipe>();

        if (constraint != null && recipesCopy != null)
        {

            for (Recipe recipe : recipesCopy)
            {
                if(recipe.getName().contains(constraint))
                {
                    tempList.add(recipe);
                }
            }

            filterResults.values = tempList;
            filterResults.count = tempList.size();
        }
        return filterResults;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results)
    {
        recipes.clear();
        recipes.addAll((ArrayList<Recipe>) results.values);

        if (results.count > 0) {
            recipeArrayAdapter.notifyDataSetChanged();
        } else {
            recipeArrayAdapter.notifyDataSetInvalidated();
        }
    }
}
