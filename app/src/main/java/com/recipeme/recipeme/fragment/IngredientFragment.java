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

import com.recipeme.recipeme.ListViewOnClickListener;
import com.recipeme.recipeme.R;
import com.recipeme.recipeme.adapter.IngredientAdapterFactory;
import com.recipeme.recipeme.fragment.RecipeFragment;

public class IngredientFragment extends Fragment
{
    private final IngredientAdapterFactory ingredientAdapterFactory = new IngredientAdapterFactory();
    private View root = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (root == null)
        {
            root = inflater.inflate(R.layout.activity_ingredient, container, false);

            final ListViewOnClickListener listViewOnClickListener = (ListViewOnClickListener) getArguments().get("Listener");
            PagerTabStrip strip = (PagerTabStrip) root.findViewById(R.id.titleStrip);


            FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    RecipeFragment fragment = new RecipeFragment();

                    //todo:find batter way to salve solution.
                    fragment.setIngredients(listViewOnClickListener.getIngredients());

                    startFragment(fragment);
                }
            });


            strip.setBackgroundColor(Color.parseColor("#ff535451"));
            strip.setTextColor(Color.WHITE);

            FragmentStatePagerAdapter ingredientAdapter =
                    ingredientAdapterFactory.createFrom(getFragmentManager(), root.getContext(), listViewOnClickListener);
            ViewPager viewPager = (ViewPager) root.findViewById(R.id.tabspager);
            viewPager.setAdapter(ingredientAdapter);
        }

        root.setVisibility(View.VISIBLE);
        return root;
    }

    private <T extends Fragment> void startFragment(T fragment)
    {
        getFragmentManager().beginTransaction()
                .replace(R.id.drawer_layout, fragment)
                .addToBackStack("tag")
                .commit();
    }
}
