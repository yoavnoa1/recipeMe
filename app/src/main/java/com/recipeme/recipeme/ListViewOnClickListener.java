package com.recipeme.recipeme;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
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
        if (ingredients.contains(ingredient))
        {
            ingredients.remove(ingredient);
            view.setBackground(new ColorDrawable(Color.WHITE));

        }
        else
        {
            ingredients.add(ingredient);
            view.setBackground(new ColorDrawable(Color.BLUE));
        }
    }

    public List<Ingredient> getIngredients()
    {
        return ingredients;
    }
}
