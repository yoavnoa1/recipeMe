package com.recipeme.recipeme.model;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.recipeme.recipeme.entities.Dairy;
import com.recipeme.recipeme.entities.GrainsAndBeans;
import com.recipeme.recipeme.entities.Ingredient;
import com.recipeme.recipeme.entities.Oils;
import com.recipeme.recipeme.entities.Other;
import com.recipeme.recipeme.entities.Protein;
import com.recipeme.recipeme.entities.Recipe;
import com.recipeme.recipeme.entities.Sweets;
import com.recipeme.recipeme.entities.VegetablesAndFruits;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Model
{
    public List<Dairy> fetchDairies()
    {
        ParseQuery<ParseObject> query = new ParseQuery<>("Dairy");
        List<Dairy> dairies = new ArrayList<>();
        try
        {
            List<ParseObject> parseObjects = query.find();

            if (!parseObjects.isEmpty())
            {
                for (ParseObject parseObject : parseObjects)
                {
                    dairies.add(parseDairy(parseObject));
                }
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return dairies;
    }

    private Dairy parseDairy(ParseObject parseObject)
    {
        Dairy dairy = new Dairy();
        dairy.setId(parseObject.getObjectId());
        dairy.setName(parseObject.get("Name").toString());

        return dairy;
    }

    public List<GrainsAndBeans> fetchGrainsAndBeans()
    {
        ParseQuery<ParseObject> query = new ParseQuery<>("GrainsAndBeans");
        List<GrainsAndBeans> grainsAndBeans = new ArrayList<>();
        try
        {
            List<ParseObject> parseObjects = query.find();

            if (!parseObjects.isEmpty())
            {
                for (ParseObject parseObject : parseObjects)
                {
                    grainsAndBeans.add(parseGrainsAndBeans(parseObject));
                }
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return grainsAndBeans;
    }

    private GrainsAndBeans parseGrainsAndBeans(ParseObject parseObject)
    {
        GrainsAndBeans grainsAndBeans = new GrainsAndBeans();
        grainsAndBeans.setId(parseObject.getObjectId());
        grainsAndBeans.setName(parseObject.get("Name").toString());

        return grainsAndBeans;
    }

    public List<Oils> fetchOils()
    {
        ParseQuery<ParseObject> query = new ParseQuery<>("Oils");
        List<Oils> oilses = new ArrayList<>();
        try
        {
            List<ParseObject> parseObjects = query.find();

            if (!parseObjects.isEmpty())
            {
                for (ParseObject parseObject : parseObjects)
                {
                    oilses.add(parseOils(parseObject));
                }
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return oilses;
    }

    private Oils parseOils(ParseObject parseObject)
    {
        Oils oils = new Oils();
        oils.setId(parseObject.getObjectId());
        oils.setName(parseObject.get("Name").toString());

        return oils;
    }

    public List<Other> fetchOther()
    {
        ParseQuery<ParseObject> query = new ParseQuery<>("Other");
        List<Other> others = new ArrayList<>();
        try
        {
            List<ParseObject> parseObjects = query.find();

            if (!parseObjects.isEmpty())
            {
                for (ParseObject parseObject : parseObjects)
                {
                    others.add(parseOther(parseObject));
                }
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return others;
    }

    private Other parseOther(ParseObject parseObject)
    {
        Other other = new Other();
        other.setId(parseObject.getObjectId());
        other.setName(parseObject.get("Name").toString());

        return other;
    }

    public List<Protein> fetchProtein()
    {
        ParseQuery<ParseObject> query = new ParseQuery<>("Protein");
        List<Protein> proteins = new ArrayList<>();
        try
        {
            List<ParseObject> parseObjects = query.find();

            if (!parseObjects.isEmpty())
            {
                for (ParseObject parseObject : parseObjects)
                {
                    proteins.add(parseProtein(parseObject));
                }
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return proteins;
    }

    private Protein parseProtein(ParseObject parseObject)
    {
        Protein protein = new Protein();
        protein.setId(parseObject.getObjectId());
        protein.setName(parseObject.get("Name").toString());

        return protein;
    }

    public List<Sweets> fetchSweets()
    {
        ParseQuery<ParseObject> query = new ParseQuery<>("Sweets");
        List<Sweets> sweetses = new ArrayList<>();
        try
        {
            List<ParseObject> parseObjects = query.find();

            if (!parseObjects.isEmpty())
            {
                for (ParseObject parseObject : parseObjects)
                {
                    sweetses.add(parseSweets(parseObject));
                }
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return sweetses;
    }

    private Sweets parseSweets(ParseObject parseObject)
    {
        Sweets sweets = new Sweets();
        sweets.setId(parseObject.getObjectId());
        sweets.setName(parseObject.get("Name").toString());

        return sweets;
    }

    public List<VegetablesAndFruits> fetchVegetablesAndFruits()
    {
        ParseQuery<ParseObject> query = new ParseQuery<>("VegetablesAndFruits");
        List<VegetablesAndFruits> vegetablesAndFruitses = new ArrayList<>();
        try
        {
            List<ParseObject> parseObjects = query.find();

            if (!parseObjects.isEmpty())
            {
                for (ParseObject parseObject : parseObjects)
                {
                    vegetablesAndFruitses.add(parseVegetablesAndFruits(parseObject));
                }
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return vegetablesAndFruitses;
    }

    private VegetablesAndFruits parseVegetablesAndFruits(ParseObject parseObject)
    {
        VegetablesAndFruits vegetablesAndFruits = new VegetablesAndFruits();
        vegetablesAndFruits.setId(parseObject.getObjectId());
        vegetablesAndFruits.setName(parseObject.get("Name").toString());

        return vegetablesAndFruits;
    }

    public Collection<Recipe> fetchRecipesBy(List<Ingredient> ingredients)
    {
        HashMap<String, List<String>> map = Maps.newHashMap();

        for (Ingredient ingredient : ingredients)
        {
            String key = ingredient.getClass().toString();
            if (map.containsKey(key))
            {
                map.get(key).add(ingredient.getName());
            }
            else
            {
                map.put(key, Lists.newArrayList(ingredient.getName()));
            }
        }


        List<ParseObject> parseObjects = Lists.newArrayList();
        for (String key : map.keySet())
        {
            parseObjects.addAll(fetchBy(key, map.get(key)));
        }

        ParseQuery<ParseObject> query = new ParseQuery<>("Recipe");
        query.include("RSIngredient");
        query.whereContainsAll("RSIngredient", parseObjects);
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
