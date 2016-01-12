package com.recipeme.recipeme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.recipeme.recipeme.adapter.CommandToRecipeAdapter;
import com.recipeme.recipeme.entities.CommandToRecipe;
import com.recipeme.recipeme.entities.Recipe;
import com.recipeme.recipeme.model.DbHelper;
import com.recipeme.recipeme.model.Model;

import java.util.List;

public class RecipeDeatilsActivity extends AppCompatActivity
{
    private static Recipe recipe;
    private DbHelper dbHelper;
    private boolean isFavorite = false;
    private final Model model = new Model();

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

        setLikeButton();

        setShareButton();

        setCommandList();

        setCommandButton();
    }

    private void setCommandButton()
    {
        Button sendButton = (Button) findViewById(R.id.send_btn);

        sendButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                    
            }
        });
    }

    private void setCommandList()
    {
        ListView listView = (ListView) findViewById(R.id.command_list);

        List<CommandToRecipe> commandToRecipesList = model.getCommandToRecipesBy(recipe.getId());

        listView.setAdapter(new CommandToRecipeAdapter(getBaseContext(), commandToRecipesList));
    }

    private void setShareButton()
    {
        ImageButton shareButton = (ImageButton) findViewById(R.id.share_button);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");

                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, recipe.getName());
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                       "זמן:" + recipe.getTime() + " | " + "כשרות:" + recipe.getKosher() + " | " + "רמת קושי:" + recipe.getLevel() + "\n" +
                                recipe.getIngredient() + "\n" +
                                recipe.getPreparation()

                );

                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
    }

    private void setLikeButton()
    {
        final TextView likeTextView = (TextView) findViewById(R.id.like_text_view);
        final ImageButton button = (ImageButton) findViewById(R.id.like_button);
        final String id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        final Boolean recipeHaveLike = model.recipeHaveLike(recipe.getId(), id);

        if(recipeHaveLike)
        {
            button.setImageDrawable(getResources().getDrawable(R.drawable.dislike));
        }
        else
        {
            button.setImageDrawable(getResources().getDrawable(R.drawable.like));
        }

        int likeOfRecipe = model.getCountLikeOfRecipe(recipe.getId());

        likeTextView.setText(likeOfRecipe + " אהבו את המתכון");

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(model.recipeHaveLike(recipe.getId(), id))
                {
                    model.deleteRecipeLikeBy(recipe.getId(), id);
                    button.setImageDrawable(getResources().getDrawable(R.drawable.like));

                    Snackbar snackbar = Snackbar
                            .make(v, "לא אהבת את המתכון", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
                else
                {
                    ParseObject likeToRecipeByUser = new ParseObject("LikeToRecipeByUser");

                    likeToRecipeByUser.put("RecipeId", recipe.getId());
                    likeToRecipeByUser.put("DeviceId", id);

                    likeToRecipeByUser.saveInBackground();

                    Snackbar snackbar = Snackbar
                            .make(v, "אהבת את המתכון", Snackbar.LENGTH_SHORT);
                    snackbar.show();

                    button.setImageDrawable(getResources().getDrawable(R.drawable.dislike));
                }

                int likeOfRecipe = model.getCountLikeOfRecipe(recipe.getId());

                likeTextView.setText(likeOfRecipe + " אהבו את המתכון");
            }
        });
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
