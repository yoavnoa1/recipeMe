package com.recipeme.recipeme.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.recipeme.recipeme.TextChangedListener;
import com.recipeme.recipeme.adapter.IngredientRowAdapter;
import com.recipeme.recipeme.ListViewOnClickListener;
import com.recipeme.recipeme.R;
import com.recipeme.recipeme.entities.Protein;
import com.recipeme.recipeme.model.Model;

import java.util.List;

public class ProteinFragment extends Fragment
{
    private View view = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (view == null)
        {
            view = inflater.inflate(R.layout.fragment_ingredients, container, false);

            List<Protein> proteins = new Model().fetchIngredients(Protein.class);
            IngredientRowAdapter<Protein> rowAdapter = new IngredientRowAdapter<>(getLayoutInflater(savedInstanceState), proteins, getActivity().getBaseContext());
            ListViewOnClickListener listViewOnClickListener = (ListViewOnClickListener) getArguments().get("Listener");
            ListView list = (ListView) view.findViewById(R.id.ingredient_listView);
            list.setAdapter(rowAdapter);
            list.setOnItemClickListener(listViewOnClickListener);

            EditText query = (EditText) view.findViewById(R.id.edit_query);
            query.addTextChangedListener(new TextChangedListener<>(rowAdapter));
        }

        return view;
    }
}
