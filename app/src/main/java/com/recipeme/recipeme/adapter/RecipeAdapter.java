package com.recipeme.recipeme.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class RecipeAdapter extends FragmentStatePagerAdapter
{
    private final Context context;
    private final List<Fragment> fragments;
    private final List<Integer> titles;

    public RecipeAdapter(FragmentManager fm, Context context, List<Fragment> fragments, List<Integer> titles)
    {
        super(fm);
        this.context = context;
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int i)
    {
        return fragments.get(i);
    }

    @Override
    public int getCount()
    {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return context.getString(titles.get(position));
    }
}
