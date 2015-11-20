package com.recipeme.recipeme;

import android.text.Editable;
import android.text.TextWatcher;

import com.recipeme.recipeme.adapter.IngredientRowAdapter;
import com.recipeme.recipeme.entities.Ingredient;

public class TextChangedListener<T extends Ingredient> implements TextWatcher
{
    private final IngredientRowAdapter<T> rowAdapter;

    public TextChangedListener(IngredientRowAdapter<T> rowAdapter)
    {
        this.rowAdapter = rowAdapter;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        rowAdapter.getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
