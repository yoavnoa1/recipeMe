package com.recipeme.recipeme.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.recipeme.recipeme.R;
import com.recipeme.recipeme.entities.Recipe;

import java.util.List;


public class RecipeCardAdapter extends RecyclerView.Adapter
{
    private final List<Recipe> recipes;

    public RecipeCardAdapter(List<Recipe> recipes)
    {
        this.recipes = recipes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card_item, parent, false);

        return new RecyclerView.ViewHolder(inflate) {};
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        TextView textView = (TextView) holder.itemView.findViewById(R.id.cardTitle);
        ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.imageView);
        Recipe recipe = recipes.get(position);
        textView.setText(recipe.getName());

//        byte[] picture = recipe.getPicture();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.length);
//        Drawable drawable = new BitmapDrawable(bitmap);
//        imageView.setBackground(drawable);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
