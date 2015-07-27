package com.recipeme.recipeme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.google.common.collect.Lists;
import com.recipeme.recipeme.fragment.DairyFragment;
import com.recipeme.recipeme.fragment.GrainsAndBeansFragment;
import com.recipeme.recipeme.fragment.OilsFragment;
import com.recipeme.recipeme.fragment.SweetsFragment;

import java.util.List;

public class IngredientAdapterFactory
{
    public FragmentStatePagerAdapter createFrom(FragmentManager fm, Context context, ListViewOnClickListener listViewOnClickListener)
    {
        List<Integer> pageTitleList = createPageTitleList();

        Bundle bundle = new Bundle();
        bundle.putSerializable("Listener", listViewOnClickListener);

        List<Fragment> paperTabs = createPaperTabs(bundle);

        return new IngredientAdapter(fm, context, pageTitleList, paperTabs);
    }

    private List<Fragment> createPaperTabs(Bundle bundle)
    {
        List<Fragment> fragments = Lists.newArrayList();

        DairyFragment dairyFragment = new DairyFragment();
        dairyFragment.setArguments(bundle);
        fragments.add(dairyFragment);

        OilsFragment oilsFragment = new OilsFragment();
        oilsFragment.setArguments(bundle);
        fragments.add(oilsFragment);

        SweetsFragment sweetsFragment = new SweetsFragment();
        sweetsFragment.setArguments(bundle);
        fragments.add(sweetsFragment);

        GrainsAndBeansFragment grainsAndBeansFragment = new GrainsAndBeansFragment();
        grainsAndBeansFragment.setArguments(bundle);
        fragments.add(grainsAndBeansFragment);

        return fragments;
    }


    private List<Integer> createPageTitleList()
    {
        List<Integer> titles = Lists.newArrayList();

        titles.add(R.string.title1);
        titles.add(R.string.title2);
        titles.add(R.string.title3);
        titles.add(R.string.title4);

        return titles;
    }
}
