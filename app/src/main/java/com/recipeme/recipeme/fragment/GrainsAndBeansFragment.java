package com.recipeme.recipeme.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.recipeme.recipeme.IngredientRowAdapter;
import com.recipeme.recipeme.ListViewOnClickListener;
import com.recipeme.recipeme.R;
import com.recipeme.recipeme.entities.GrainsAndBeans;
import com.recipeme.recipeme.model.Model;

import java.util.List;

public class GrainsAndBeansFragment extends Fragment
{
    IngredientRowAdapter<GrainsAndBeans> rowAdapter;
    ListViewOnClickListener listViewOnClickListener;
    View view = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (view == null)
        {
            view = inflater.inflate(R.layout.fragment_grains_and_beans, container, false);

            List<GrainsAndBeans> dairies = new Model().fetchGrainsAndBeans();
            rowAdapter = new IngredientRowAdapter<>(getLayoutInflater(savedInstanceState), dairies, getActivity().getBaseContext());
            listViewOnClickListener = (ListViewOnClickListener) getArguments().get("Listener");
            ListView list = (ListView) view.findViewById(R.id.grains_and_beans_listView);
            list.setAdapter(rowAdapter);
            list.setOnItemClickListener(listViewOnClickListener);
        }

        return view;
    }
}
