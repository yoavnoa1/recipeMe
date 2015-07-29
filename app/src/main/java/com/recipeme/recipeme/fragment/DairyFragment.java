package com.recipeme.recipeme.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.recipeme.recipeme.adapter.IngredientRowAdapter;
import com.recipeme.recipeme.ListViewOnClickListener;
import com.recipeme.recipeme.R;
import com.recipeme.recipeme.entities.Dairy;
import com.recipeme.recipeme.model.Model;

import java.util.List;

public class DairyFragment extends Fragment
{
    private IngredientRowAdapter<Dairy> rowAdapter;
    private ListViewOnClickListener listViewOnClickListener;
    private View view = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (view == null)
        {
            view = inflater.inflate(R.layout.fragment_ingredients, container, false);

            List<Dairy> dairies = new Model().fetchIngredients(Dairy.class);
            rowAdapter = new IngredientRowAdapter<>(inflater, dairies, getActivity().getBaseContext());
            listViewOnClickListener = (ListViewOnClickListener) getArguments().get("Listener");
            ListView list = (ListView) view.findViewById(R.id.ingredient_listView);
            EditText query = (EditText) view.findViewById(R.id.edit_query);
            list.setAdapter(rowAdapter);
            list.setOnItemClickListener(listViewOnClickListener);

            query.addTextChangedListener(new TextWatcher()
            {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after)
                {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count)
                {
                    rowAdapter.getFilter().filter(s);
                }

                @Override
                public void afterTextChanged(Editable s)
                {

                }
            });
        }

        return view;
    }
}
