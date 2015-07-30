package com.recipeme.recipeme.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.recipeme.recipeme.R;
import com.recipeme.recipeme.entities.Recipe;

import java.util.List;

public class RecipeRowAdapter extends BaseAdapter
{
    private final List<Recipe> recipes;
    private final LayoutInflater layoutInflater;

    public RecipeRowAdapter(List<Recipe> recipes, LayoutInflater layoutInflater)
    {
        this.recipes = recipes;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount()
    {
        return recipes.size();
    }

    @Override
    public Object getItem(int position)
    {
        return recipes.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.recipe_row_layout, null);
        }

        byte[] picture = recipes.get(position).getPicture();
        Bitmap bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.length);

        ((ImageView)convertView.findViewById(R.id.avatar)).setImageBitmap(bitmap);
        ((TextView)convertView.findViewById(R.id.recipe_name)).setText(recipes.get(position).getName());

        return convertView;
    }
}
