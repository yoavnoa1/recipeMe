package com.recipeme.recipeme.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.recipeme.recipeme.adapter.IngredientRowAdapter;
import com.recipeme.recipeme.ListViewOnClickListener;
import com.recipeme.recipeme.R;
import com.recipeme.recipeme.entities.Oils;
import com.recipeme.recipeme.model.Model;

import java.util.List;

public class OilsFragment  extends Fragment
{
    private IngredientRowAdapter<Oils> rowAdapter;
    private ListViewOnClickListener listViewOnClickListener;
    private View view = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if(view == null)
        {
           // view = inflater.inflate(R.layout.fragment_oils, container, false);

            view = inflater.inflate(R.layout.fragment_ingredients, container, false);

            List<Oils> oils = new Model().fetchIngredients(Oils.class);
            rowAdapter = new IngredientRowAdapter<>(inflater, oils, getActivity().getBaseContext());
            listViewOnClickListener = (ListViewOnClickListener) getArguments().get("Listener");
            ListView list = (ListView) view.findViewById(R.id.ingredient_listView);
            list.setAdapter(rowAdapter);
            list.setOnItemClickListener(listViewOnClickListener);
        }

        return view;
    }
}
