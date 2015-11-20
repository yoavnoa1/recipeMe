package com.recipeme.recipeme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.recipeme.recipeme.entities.Recipe;

public class RecipeDeatilsActivity extends AppCompatActivity
{
    private static Recipe recipe;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recpie_deatils);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(recipe.getName());

        loadBackdrop();
    }

    public static void set(Recipe input)
    {
        recipe = input;
    }

    private void loadBackdrop()
    {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);

        byte[] picture = recipe.getPicture();
        Bitmap bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.length);
        Drawable drawable = new BitmapDrawable(bitmap);
        imageView.setImageDrawable(drawable);

        ((TextView) findViewById(R.id.time)).setText(recipe.getTime());
        ((TextView) findViewById(R.id.level)).setText(recipe.getLevel());
        ((TextView) findViewById(R.id.kosher)).setText(recipe.getKosher());
        ((TextView) findViewById(R.id.prep)).setText(recipe.getPreparation());
        ((TextView) findViewById(R.id.ing)).setText(recipe.getIngredient());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_recpie_deatils, menu);
        return true;
    }
}
