package com.recipeme.recipeme.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.recipeme.recipeme.R;
import com.recipeme.recipeme.adapter.RecipeCardAdapter;
import com.recipeme.recipeme.entities.Recipe;
import com.recipeme.recipeme.model.Model;

import java.util.ArrayList;
import java.util.Collection;

public class MainPageFragment extends Fragment
{
    private View view = null;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (view == null)
        {
            view = inflater.inflate(R.layout.fragment_main_page, container, false);
            Model model = new Model();

            Collection<Recipe> recipes = model.getAll();

            ArrayList<Recipe> recipes1 = Lists.newArrayList(recipes);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(linearLayoutManager);

            RecipeCardAdapter recipeCardAdapter = new RecipeCardAdapter(recipes1);
            recyclerView.setAdapter(recipeCardAdapter);

        }

        return view;
    }
}
