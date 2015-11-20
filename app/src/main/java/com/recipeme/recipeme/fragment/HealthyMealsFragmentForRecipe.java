package com.recipeme.recipeme.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.common.collect.Lists;
import com.parse.ParseObject;
import com.recipeme.recipeme.R;
import com.recipeme.recipeme.RecipeDeatilsActivity;
import com.recipeme.recipeme.adapter.RecipeRowAdapter;
import com.recipeme.recipeme.entities.Recipe;
import com.recipeme.recipeme.model.Model;

import java.util.Collection;

public class HealthyMealsFragmentForRecipe extends Fragment
{
    private View view = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (view == null)
        {
            view = inflater.inflate(R.layout.fragment_recipe2, container, false);


            Model model = new Model();
            ParseObject categoryRecipe = model.fetchCategoryRecipeBy("ארוחות בריאות").iterator().next();

            Collection<Recipe> recipes = model.fetchRecipesBy(categoryRecipe);
            final RecipeRowAdapter recipeRowAdapter = new RecipeRowAdapter(Lists.newArrayList(recipes), inflater, getActivity().getBaseContext());

            ListView list = (ListView) view.findViewById(R.id.recipe_listView);
            EditText query = (EditText) view.findViewById(R.id.recipe_query);
            list.setAdapter(recipeRowAdapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Recipe recipe = (Recipe) parent.getItemAtPosition(position);

                    RecipeDeatilsActivity.set(recipe);
                    Intent intent = new Intent(view.getContext(), RecipeDeatilsActivity.class);

                    startActivity(intent);
                }
            });

            query.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    recipeRowAdapter.getFilter().filter(s);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        return view;
    }
}
