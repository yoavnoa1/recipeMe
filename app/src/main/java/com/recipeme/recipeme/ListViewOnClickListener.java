package com.recipeme.recipeme;

import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

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
            view.setBackgroundColor(Color.parseColor("#FFF5F5F5"));
            ((TextView)view.findViewById(R.id.textView)).setTextColor(Color.BLACK);

        }
        else
        {
            ingredients.add(ingredient);
            view.setBackgroundColor(Color.parseColor("#FF595A57"));
            ((TextView)view.findViewById(R.id.textView)).setTextColor(Color.WHITE);
        }
    }

    public List<Ingredient> getIngredients()
    {
        return ingredients;
    }
}
