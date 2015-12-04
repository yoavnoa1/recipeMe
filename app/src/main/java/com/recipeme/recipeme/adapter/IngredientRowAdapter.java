package com.recipeme.recipeme.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.recipeme.recipeme.IngredientFilter;
import com.recipeme.recipeme.ListViewOnClickListener;
import com.recipeme.recipeme.R;
import com.recipeme.recipeme.entities.Ingredient;

import java.util.List;

public class IngredientRowAdapter<T extends Ingredient> extends ArrayAdapter<T> implements Filterable
{
    private final List<T> ingredients;
    private final LayoutInflater layoutInflater;
    private IngredientFilter<T> filter;
    private final ListViewOnClickListener listViewOnClickListener;

    public IngredientRowAdapter(LayoutInflater layoutInflater, List<T> ingredients, Context context, ListViewOnClickListener listViewOnClickListener)
    {
        super(context, R.layout.ingredient_row_layout, ingredients);
        this.ingredients = ingredients;
        this.layoutInflater = layoutInflater;
        this.listViewOnClickListener = listViewOnClickListener;

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

        if (listViewOnClickListener.getIngredients().contains(ingredients.get(position)))
        {
            vi.setBackgroundColor(Color.parseColor("#FF595A57"));
            ((TextView) vi.findViewById(R.id.textView)).setTextColor(Color.WHITE);
        }
        else
        {
            vi.setBackgroundColor(Color.parseColor("#FFF5F5F5"));
            ((TextView) vi.findViewById(R.id.textView)).setTextColor(Color.BLACK);
        }
        ((TextView) vi.findViewById(R.id.textView)).setText(ingredients.get(position).getName());

        return vi;
    }

    @Override
    public Filter getFilter()
    {
        return filter;
    }
}
