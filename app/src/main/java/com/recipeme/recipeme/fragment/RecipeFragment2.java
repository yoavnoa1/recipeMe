package com.recipeme.recipeme.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recipeme.recipeme.R;
import com.recipeme.recipeme.adapter.RecipeAdapterFactory;

//todo:change the name.
public class RecipeFragment2 extends Fragment
{
    private View view = null;
    private final RecipeAdapterFactory recipeAdapterFactory = new RecipeAdapterFactory();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (view == null)
        {
            view = inflater.inflate(R.layout.activty_recipe, container, false);

            PagerTabStrip strip = (PagerTabStrip) view.findViewById(R.id.titleStrip);

            strip.setBackgroundColor(Color.parseColor("#ff535451"));
            strip.setTextColor(Color.WHITE);

            FragmentStatePagerAdapter recipeAdapter = recipeAdapterFactory.createFrom(getFragmentManager(), view.getContext());
            ViewPager viewPager = (ViewPager) view.findViewById(R.id.tabspager);
            viewPager.setAdapter(recipeAdapter);
        }

        return view;
    }
}
