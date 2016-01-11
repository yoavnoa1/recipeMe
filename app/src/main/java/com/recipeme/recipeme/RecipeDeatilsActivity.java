package com.recipeme.recipeme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.recipeme.recipeme.entities.Recipe;
import com.recipeme.recipeme.model.DbHelper;

public class RecipeDeatilsActivity extends AppCompatActivity
{
    private static Recipe recipe;
    private DbHelper dbHelper;
    private boolean isFavorite = false;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recpie_deatils);

        dbHelper = new DbHelper(this);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(recipe.getName());

        loadBackdrop();

        setFavoriteButton();
    }

    private void setFavoriteButton()
    {
        final FloatingActionButton button = (FloatingActionButton) findViewById(R.id.fav_button);

        isFavorite = dbHelper.isFavoriteExists(recipe.getId());

        if(isFavorite)
        {
            button.setImageDrawable(getResources().getDrawable(R.drawable.star_fav_full));
        }

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (isFavorite)
                {
                    dbHelper.removeFavorite(recipe);
                    isFavorite = false;
                    button.setImageDrawable(getResources().getDrawable(R.drawable.abc_btn_rating_star_off_mtrl_alpha));

                    Snackbar snackbar = Snackbar
                            .make(v, "מתכון נמחק מהמועדפים", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
                else
                {
                    dbHelper.insertFavorite(recipe);
                    isFavorite = true;
                    button.setImageDrawable(getResources().getDrawable(R.drawable.star_fav_full));

                    Snackbar snackbar = Snackbar
                            .make(v, "מתכון נוסף למועדפים", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });
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
