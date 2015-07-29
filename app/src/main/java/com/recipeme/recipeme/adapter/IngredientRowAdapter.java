package com.recipeme.recipeme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.recipeme.recipeme.IngredientFilter;
import com.recipeme.recipeme.R;
import com.recipeme.recipeme.entities.Ingredient;

import java.util.List;

public class IngredientRowAdapter<T extends Ingredient> extends ArrayAdapter<T> implements Filterable
{
    private final List<T> ingredients;
    private final LayoutInflater layoutInflater;
    private IngredientFilter<T> filter;

    public IngredientRowAdapter(LayoutInflater layoutInflater, List<T> ingredients, Context context)
    {
        super(context, R.layout.ingredient_row_layout, ingredients);
        this.ingredients = ingredients;
        this.layoutInflater = layoutInflater;

        filter = new IngredientFilter<>(ingredients, this);
    }

    @Override
    public int getCount()
    {
        return ingredients.size();
    }

    @Override
    public T getItem(int position)
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
            vi = layoutInflater.inflate(R.layout.ingredient_row_layout, null);
        }

        ((TextView)vi.findViewById(R.id.textView)).setText(ingredients.get(position).getName());

        return vi;
    }

    @Override
    public Filter getFilter()
    {
        return filter;
    }
}
