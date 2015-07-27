package com.recipeme.recipeme;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.recipeme.recipeme.fragment.DairyFragment;
import com.recipeme.recipeme.fragment.GrainsAndBeansFragment;
import com.recipeme.recipeme.fragment.OilsFragment;
import com.recipeme.recipeme.fragment.SweetsFragment;

import java.util.ArrayList;

public class IngredientAdapter extends FragmentStatePagerAdapter
{
    private final ArrayList<Integer> titles = new ArrayList<>();
    private final Context context;
    private final ArrayList<Fragment> fragments = new ArrayList<>();

    public IngredientAdapter(FragmentManager fm, Context context, ListViewOnClickListener listViewOnClickListener)
    {
        super(fm);
        this.context = context;
        titles.add(R.string.title1);
        titles.add(R.string.title2);
        titles.add(R.string.title3);
        titles.add(R.string.title4);
        DairyFragment fragment = new DairyFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Listener", listViewOnClickListener);
        fragment.setArguments(bundle);
        fragments.add(fragment);
        fragments.add(new OilsFragment());
        fragments.add(new SweetsFragment());
        GrainsAndBeansFragment grainsAndBeansFragment = new GrainsAndBeansFragment();
        grainsAndBeansFragment.setArguments(bundle);
        fragments.add(grainsAndBeansFragment);
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
    public CharSequence getPageTitle(int i)
    {
        return context.getString(titles.get(i));
    }
}
