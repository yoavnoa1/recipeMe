package com.recipeme.recipeme.model;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.recipeme.recipeme.entities.Ingredient;
import com.recipeme.recipeme.entities.Recipe;
import com.recipeme.recipeme.model.parser.IngredientParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Model
{
    public <T extends Ingredient> List<T> fetchIngredients(Class<T> ingredientClass)
    {
        ParseQuery<ParseObject> query = new ParseQuery<>(ingredientClass.getSimpleName());
        List<T> dairies = new ArrayList<>();
        try
        {
            List<ParseObject> parseObjects = query.find();

            if (!parseObjects.isEmpty())
            {
                for (ParseObject parseObject : parseObjects)
                {
                    IngredientParser<T> parser = new IngredientParser<>();
                    dairies.add(parser.parse(parseObject, ingredientClass));
                }
            }
        }
        catch (ParseException | InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
        }

        return dairies;
    }

    public Collection<Recipe> fetchRecipesBy(List<Ingredient> ingredients)
    {
        HashMap<String, List<String>> map = createIngredientsMap(ingredients);

        List<ParseObject> parseObjects = Lists.newArrayList();
        for (String key : map.keySet())
        {
            parseObjects.addAll(fetchBy(key, map.get(key)));
        }

        ParseQuery<ParseObject> query = new ParseQuery<>("Recipe");
        query.include("RSIngredient");
        query.whereContainedIn("RSIngredient", parseObjects);

        List<ParseObject> queriedObjects = Lists.newArrayList();
        try
        {
            queriedObjects = query.find();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return Collections2.transform(queriedObjects, new Function<ParseObject, Recipe>()
        {
            @Override
            public Recipe apply(ParseObject input)
            {
                Recipe recipe = new Recipe();
                recipe.setId(input.getString("objectId"));
                recipe.setName(input.getString("Name"));
                recipe.setPreparation(input.getString("Preparation"));
                recipe.setIngredient(input.getString("Ingredient"));
                recipe.setTime(input.getString("Time"));
                recipe.setLevel(input.getInt("Level"));
                recipe.setKosher(input.getString("Kosher"));
                try
                {
                    recipe.setPicture(((ParseFile) input.get("Picture")).getData());
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }

                return recipe;
            }
        });
    }

    private HashMap<String, List<String>> createIngredientsMap(List<Ingredient> ingredients)
    {
        HashMap<String, List<String>> map = Maps.newHashMap();

        for (Ingredient ingredient : ingredients)
        {
            String key = ingredient.getClass().getSimpleName();
            if (map.containsKey(key))
            {
                map.get(key).add(ingredient.getName());
            }
            else
            {
                map.put(key, Lists.newArrayList(ingredient.getName()));
            }
        }
        return map;
    }

    private List<ParseObject> fetchBy(String className, List<String> ingredients)
    {
        ParseQuery<ParseObject> query = new ParseQuery<>(className);

        try
        {
            for (String ingredient : ingredients)
            {
                query.whereEqualTo("Name", ingredient);
            }

            return query.find();

        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return Lists.newArrayList();
    }
}
