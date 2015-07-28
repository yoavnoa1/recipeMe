package com.recipeme.recipeme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class IngredientFragment extends Fragment
{
    private final IngredientAdapterFactory ingredientAdapterFactory = new IngredientAdapterFactory();
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.activity_ingredient, container, false);

        ListViewOnClickListener listViewOnClickListener = (ListViewOnClickListener)getArguments().get("Listener");

        FragmentStatePagerAdapter ingredientAdapter =
                ingredientAdapterFactory.createFrom(getFragmentManager(), root.getContext(), listViewOnClickListener);
        viewPager = (ViewPager) root.findViewById(R.id.tabspager);
        viewPager.setAdapter(ingredientAdapter);

        return root;
    }


}
