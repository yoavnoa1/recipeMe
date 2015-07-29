package com.recipeme.recipeme.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.common.collect.Lists;
import com.recipeme.recipeme.R;
import com.recipeme.recipeme.adapter.RecipeRowAdapter;
import com.recipeme.recipeme.entities.Ingredient;
import com.recipeme.recipeme.entities.Recipe;
import com.recipeme.recipeme.model.Model;

import java.util.Collection;
import java.util.List;

public class RecipeFragment extends Fragment
{
    private View view = null;
    private List<Ingredient> ingredients;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_recipe, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBarRecipeFragment);
        progressBar.setVisibility(View.GONE);

        new FetchTask(inflater).execute();
        return view;
    }

    public void setIngredients(List<Ingredient> ingredients)
    {
        this.ingredients = ingredients;
    }

    class FetchTask extends AsyncTask<Void, Void, Collection<Recipe>>
    {
        private final LayoutInflater inflater;

        FetchTask(LayoutInflater inflater)
        {
            this.inflater = inflater;
        }

        @Override
        protected void onPreExecute()
        {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Collection<Recipe> doInBackground(Void... params)
        {
            return new Model().fetchRecipesBy(ingredients);
        }

        @Override
        protected void onPostExecute(Collection<Recipe> recipes)
        {
            RecipeRowAdapter rowAdapter = new RecipeRowAdapter(Lists.newArrayList(recipes), inflater);
            ListView list = (ListView) view.findViewById(R.id.recipe_listView);
            list.setAdapter(rowAdapter);
            progressBar.setVisibility(View.GONE);

            final FragmentManager fragmentManager = getFragmentManager();

            list.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    Recipe recipe = (Recipe) parent.getItemAtPosition(position);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Recipe", recipe);

                    RecipePageFragment recipePageFragment = new RecipePageFragment();

                    recipePageFragment.setArguments(bundle);

                    fragmentManager.beginTransaction()
                            .replace(R.id.container, recipePageFragment)
                            .addToBackStack("tag")
                            .commit();

                }
            });
        }
    }
}
