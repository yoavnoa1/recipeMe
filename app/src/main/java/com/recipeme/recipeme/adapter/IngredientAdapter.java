package com.recipeme.recipeme.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class IngredientAdapter extends FragmentStatePagerAdapter {
    private final Context context;
    private final List<Integer> titles;
    private final List<Fragment> fragments;

    public IngredientAdapter(FragmentManager fm,Context context,List<Integer> titles,List<Fragment> fragments)
    {
        super(fm);
        this.context = context;
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int i) {
        return context.getString(titles.get(i));
    }
}
