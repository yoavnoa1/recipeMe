package com.recipeme.recipeme;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.recipeme.recipeme.entities.Ingredient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListViewOnClickListener implements AdapterView.OnItemClickListener, Serializable
{
    private List<Ingredient> ingredients = new ArrayList<>();

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Ingredient ingredient = (Ingredient) parent.getItemAtPosition(position);
        Adapter adapter = parent.getAdapter();
        if (ingredients.contains(ingredient))
        {
            ingredients.remove(ingredient);
            adapter.getView(position, view, parent);
        }
        else
        {
            ingredients.add(ingredient);
            adapter.getView(position, view, parent);
        }
    }

    public List<Ingredient> getIngredients()
    {
        return ingredients;
    }
}
