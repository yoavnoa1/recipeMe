package com.recipeme.recipeme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.recipeme.recipeme.entities.Ingredient;

import java.util.List;

public class IngredientRowAdapter<T extends Ingredient> extends BaseAdapter
{
    private List<T> ingredients;
    private static LayoutInflater inflater = null;

    public IngredientRowAdapter(LayoutInflater layoutInflater, List<T> ingredients)
    {
        this.ingredients = ingredients;
        inflater = layoutInflater;
    }

    @Override
    public int getCount()
    {
        return ingredients.size();
    }

    @Override
    public Object getItem(int position)
    {
        return ingredients.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;

        if (convertView == null)
        {
            vi = inflater.inflate(R.layout.ingredient_row_layout, null);
        }

        ((TextView)vi.findViewById(R.id.textView)).setText(ingredients.get(position).getName());

        return vi;
    }
}
