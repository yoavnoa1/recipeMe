package com.recipeme.recipeme;

import android.widget.ArrayAdapter;
import android.widget.Filter;

import com.google.common.collect.Lists;
import com.recipeme.recipeme.entities.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class Filter2<T extends Ingredient> extends Filter
{
    private final List<T> ingredients;
    private final List<T> ingredientsCopy = Lists.newArrayList();
    private final ArrayAdapter<T> dairyArrayAdapter;

    public Filter2(List<T> ingredients, ArrayAdapter<T> dairyArrayAdapter)
    {
        this.ingredients = ingredients;
        this.dairyArrayAdapter = dairyArrayAdapter;
        ingredientsCopy.addAll(ingredients);
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint)
    {
        FilterResults filterResults = new FilterResults();
        ArrayList<T> tempList = new ArrayList<T>();

        if (constraint != null && ingredientsCopy != null)
        {

            for (T ingredient : ingredientsCopy)
            {
                if(ingredient.getName().contains(constraint))
                {
                    tempList.add(ingredient);
                }
            }

            filterResults.values = tempList;
            filterResults.count = tempList.size();
        }
        return filterResults;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void publishResults(CharSequence contraint, FilterResults results)
    {
        ingredients.clear();
        ingredients.addAll((ArrayList<T>) results.values);

        if (results.count > 0) {
            dairyArrayAdapter.notifyDataSetChanged();
        } else {
            dairyArrayAdapter.notifyDataSetInvalidated();
        }
    }
}
