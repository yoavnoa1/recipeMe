package com.recipeme.recipeme.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.recipeme.recipeme.R;
import com.recipeme.recipeme.entities.Recipe;

import java.util.List;

public class MainRecipeRowAdapter extends ArrayAdapter
{
    private final List<Recipe> recipes;
    private final LayoutInflater layoutInflater;

    public MainRecipeRowAdapter(Context context, int resource, List<Recipe> recipes, LayoutInflater layoutInflater)
    {
        super(context, resource);
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
            convertView = layoutInflater.inflate(R.layout.main_recipe_row_layout, null);
        }

        byte[] picture = recipes.get(position).getPicture();
        Bitmap bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.length);

        ((ImageView)convertView.findViewById(R.id.mainImageView)).setImageBitmap(bitmap);
        ((TextView)convertView.findViewById(R.id.main_recipe_title)).setText(recipes.get(position).getName());

        return convertView;
    }
}
