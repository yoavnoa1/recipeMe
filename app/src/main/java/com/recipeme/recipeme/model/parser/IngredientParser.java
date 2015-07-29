package com.recipeme.recipeme.model.parser;

import com.parse.ParseObject;
import com.recipeme.recipeme.entities.Ingredient;

public class IngredientParser<T extends Ingredient>
{
    public T parse(ParseObject parseObject, Class<T> ingredientClass) throws IllegalAccessException, InstantiationException
    {
        T ingredient = ingredientClass.newInstance();
        ingredient.setId(parseObject.getObjectId());
        ingredient.setName(parseObject.get("Name").toString());
        return ingredient;
    }
}
