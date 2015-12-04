package com.recipeme.recipeme.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.recipeme.recipeme.ListViewOnClickListener;
import com.recipeme.recipeme.R;
import com.recipeme.recipeme.TextChangedListener;
import com.recipeme.recipeme.adapter.IngredientRowAdapter;
import com.recipeme.recipeme.entities.Dairy;
import com.recipeme.recipeme.model.Model;

import java.util.List;

public class DairyFragment extends Fragment
{
    private View view = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (view == null)
        {
            view = inflater.inflate(R.layout.fragment_ingredients, container, false);

            List<Dairy> dairies = new Model().fetchIngredients(Dairy.class);
            ListViewOnClickListener listViewOnClickListener = (ListViewOnClickListener) getArguments().get("Listener");
            IngredientRowAdapter<Dairy> rowAdapter = new IngredientRowAdapter<>(inflater, dairies, getActivity().getBaseContext(), listViewOnClickListener);
            ListView list = (ListView) view.findViewById(R.id.ingredient_listView);
            EditText query = (EditText) view.findViewById(R.id.edit_query);
            list.setAdapter(rowAdapter);
            list.setOnItemClickListener(listViewOnClickListener);

            query.addTextChangedListener(new TextChangedListener<>(rowAdapter));
        }

        return view;
    }
}
