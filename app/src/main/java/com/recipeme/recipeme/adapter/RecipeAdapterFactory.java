package com.recipeme.recipeme.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.google.common.collect.Lists;
import com.recipeme.recipeme.R;
import com.recipeme.recipeme.fragment.BreakfastFragmentForRecipe;
import com.recipeme.recipeme.fragment.FirstCourseFragmentForRecipe;
import com.recipeme.recipeme.fragment.FishFragmentForRecipe;
import com.recipeme.recipeme.fragment.HealthyMealsFragmentForRecipe;
import com.recipeme.recipeme.fragment.MainCourseFragmentForRecipe;
import com.recipeme.recipeme.fragment.MeatFragmentForRecipe;
import com.recipeme.recipeme.fragment.PastasFragmentForRecipe;
import com.recipeme.recipeme.fragment.SaladsFragmentForRecipe;
import com.recipeme.recipeme.fragment.SoupsFragmentForRecipe;
import com.recipeme.recipeme.fragment.SweetsFragmentForRecipe;

import java.util.List;

public class RecipeAdapterFactory
{
    public RecipeAdapter createFrom(FragmentManager fragmentManager, Context context)
    {
        List<Fragment> fragments = createFragments();

        List<Integer> titleList = createPageTitleList();

        return new RecipeAdapter(fragmentManager, context, fragments, titleList);
    }

    private List<Fragment> createFragments()
    {
        List<Fragment> fragments = Lists.newArrayList();

        SweetsFragmentForRecipe sweetsFragmentForRecipe = new SweetsFragmentForRecipe();
        MeatFragmentForRecipe meatFragmentForRecipe = new MeatFragmentForRecipe();
        BreakfastFragmentForRecipe breakfastFragmentForRecipe = new BreakfastFragmentForRecipe();
        HealthyMealsFragmentForRecipe healthyMealsFragmentForRecipe = new HealthyMealsFragmentForRecipe();
        SaladsFragmentForRecipe saladsFragmentForRecipe = new SaladsFragmentForRecipe();
        SoupsFragmentForRecipe soupsFragmentForRecipe = new SoupsFragmentForRecipe();
        MainCourseFragmentForRecipe mainCourseFragmentForRecipe = new MainCourseFragmentForRecipe();
        FirstCourseFragmentForRecipe firstCourseFragmentForRecipe = new FirstCourseFragmentForRecipe();
        PastasFragmentForRecipe pastasFragmentForRecipe = new PastasFragmentForRecipe();
        FishFragmentForRecipe fishFragmentForRecipe = new FishFragmentForRecipe();

        fragments.add(sweetsFragmentForRecipe);
        fragments.add(meatFragmentForRecipe);
        fragments.add(breakfastFragmentForRecipe);
        fragments.add(healthyMealsFragmentForRecipe);
        fragments.add(saladsFragmentForRecipe);
        fragments.add(soupsFragmentForRecipe);
        fragments.add(mainCourseFragmentForRecipe);
        fragments.add(firstCourseFragmentForRecipe);
        fragments.add(pastasFragmentForRecipe);
        fragments.add(fishFragmentForRecipe);

        return fragments;
    }

    private List<Integer> createPageTitleList()
    {
        List<Integer> titles = Lists.newArrayList();

        titles.add(R.string.title8);
        titles.add(R.string.title9);
        titles.add(R.string.title10);
        titles.add(R.string.title11);
        titles.add(R.string.title12);
        titles.add(R.string.title13);
        titles.add(R.string.title14);
        titles.add(R.string.title15);
        titles.add(R.string.title16);
        titles.add(R.string.title17);

        return titles;
    }

}
