package com.recipeme.recipeme.model;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.recipeme.recipeme.entities.CommandToRecipe;
import com.recipeme.recipeme.entities.Ingredient;
import com.recipeme.recipeme.entities.LikeToRecipeByUser;
import com.recipeme.recipeme.entities.Recipe;
import com.recipeme.recipeme.model.parser.IngredientParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Model {
    public <T extends Ingredient> List<T> fetchIngredients(Class<T> ingredientClass) {
        ParseQuery<ParseObject> query = new ParseQuery<>(ingredientClass.getSimpleName());
        List<T> dairies = new ArrayList<>();
        try {
            List<ParseObject> parseObjects = query.find();

            if (!parseObjects.isEmpty()) {
                for (ParseObject parseObject : parseObjects) {
                    IngredientParser<T> parser = new IngredientParser<>();
                    dairies.add(parser.parse(parseObject, ingredientClass));
                }
            }
        } catch (ParseException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return dairies;
    }

    public List<Recipe> fetchFavorietRecipes(List<String> recipesIds) {
        ParseQuery<ParseObject> query = new ParseQuery<>("Recipe");
        try {
            List<ParseObject> parseObjects = query.whereContainedIn("objectId", recipesIds).find();

            return Lists.<Recipe>newArrayList(getTransform(parseObjects, Lists.<Ingredient>newArrayList()));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Lists.<Recipe>newArrayList();
    }

    public int getCountLikeOfRecipe(String recipeId)
    {
        ParseQuery<ParseObject> query = new ParseQuery<>("LikeToRecipeByUser");
        query.whereEqualTo("RecipeId", recipeId);

        try
        {
            return query.find().size();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public Boolean recipeHaveLike(String recipeId, String deviceId) {
        ParseQuery<ParseObject> query = new ParseQuery<>("LikeToRecipeByUser");
        query.whereEqualTo("RecipeId", recipeId);
        query.whereEqualTo("DeviceId", deviceId);

        try {
            return query.find().size() > 0;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void deleteRecipeLikeBy(String recipeId, String deviceId)
    {
        ParseQuery<ParseObject> query = new ParseQuery<>("LikeToRecipeByUser");
        query.whereEqualTo("RecipeId", recipeId);
        query.whereEqualTo("DeviceId", deviceId);


        try
        {
            List<ParseObject> objects = query.find();
            objects.get(0).delete();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Collection<Recipe> fetchRecipesBy(final List<Ingredient> ingredients) {
        HashMap<String, List<String>> map = createIngredientsMap(ingredients);

        List<ParseObject> parseObjects = Lists.newArrayList();
        for (String key : map.keySet()) {
            parseObjects.addAll(fetchBy(key, map.get(key)));
        }

        ParseQuery<ParseObject> query = new ParseQuery<>("Recipe");
        query.include("RSIngredient");
        query.whereContainedIn("RSIngredient", parseObjects);

        List<ParseObject> queriedObjects = Lists.newArrayList();
        try {
            queriedObjects = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return getTransform(queriedObjects, ingredients);
    }

    private Collection<Recipe> getTransform(List<ParseObject> queriedObjects, final List<Ingredient> ingredients) {

        Collection<Recipe> transform = Collections2.transform(queriedObjects, new Function<ParseObject, Recipe>() {
            @Override
            public Recipe apply(ParseObject input) {
                Recipe recipe = new Recipe();
                recipe.setId(input.getObjectId());
                recipe.setName(input.getString("Name"));
                recipe.setPreparation(input.getString("Preparation"));
                recipe.setIngredient(input.getString("Ingredient"));
                recipe.setTime(input.getString("Time"));
                recipe.setLevel(input.getString("Level"));
                recipe.setKosher(input.getString("Kosher"));
                try {
                    recipe.setPicture(((ParseFile) input.get("Picture")).getData());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                recipe.setLessIngredient(((List) input.get("RSIngredient")).size() - ingredients.size());

                return recipe;
            }
        });

        return transform;
    }

    public Collection<Recipe> fetchRecipesBy(ParseObject categoryRecipe) {
        ParseQuery<ParseObject> query = new ParseQuery<>("Recipe");
        query.include("RSCategories");
        query.whereEqualTo("RSCategories", categoryRecipe);

        List<ParseObject> queriedObjects = Lists.newArrayList();
        try {
            query.include("Level");
            queriedObjects = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Collections2.transform(queriedObjects, new Function<ParseObject, Recipe>() {
            @Override
            public Recipe apply(ParseObject input) {
                ParseObject recipeLevel;
                recipeLevel = input.getParseObject("Level");

                Recipe recipe = new Recipe();
                recipe.setId(input.getObjectId());
                recipe.setName(input.getString("Name"));
                recipe.setPreparation(input.getString("Preparation"));
                recipe.setIngredient(input.getString("Ingredient"));
                recipe.setTime(input.getString("Time"));
                recipe.setLevel(recipeLevel.getString("Name"));
                recipe.setKosher(input.getString("Kosher"));
                try {
                    recipe.setPicture(((ParseFile) input.get("Picture")).getData());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return recipe;
            }
        });
    }

    public Collection<Recipe> getAll() {
        List<ParseObject> queriedObjects = Lists.newArrayList();
        ParseQuery<ParseObject> query = new ParseQuery<>("Recipe");

        try {
            query.include("Level");
            queriedObjects = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Collections2.transform(queriedObjects, new Function<ParseObject, Recipe>() {
            @Override
            public Recipe apply(ParseObject input) {
                ParseObject recipeLevel;
                recipeLevel = input.getParseObject("Level");

                Recipe recipe = new Recipe();
                recipe.setId(input.getString("objectId"));
                recipe.setName(input.getString("Name"));
                recipe.setPreparation(input.getString("Preparation"));
                recipe.setIngredient(input.getString("Ingredient"));
                recipe.setTime(input.getString("Time"));
                recipe.setLevel(recipeLevel.getString("Level"));
                recipe.setKosher(input.getString("Kosher"));
                try {
                    recipe.setPicture(((ParseFile) input.get("Picture")).getData());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return recipe;
            }
        });

    }

    private HashMap<String, List<String>> createIngredientsMap(List<Ingredient> ingredients) {
        HashMap<String, List<String>> map = Maps.newHashMap();

        for (Ingredient ingredient : ingredients) {
            String key = ingredient.getClass().getSimpleName();
            if (map.containsKey(key)) {
                map.get(key).add(ingredient.getName());
            } else {
                map.put(key, Lists.newArrayList(ingredient.getName()));
            }
        }
        return map;
    }

    public Collection<ParseObject> fetchCategoryRecipeBy(String nameCategory) {
        ParseQuery<ParseObject> query = new ParseQuery<>("CategoriesRecipes");

        query.whereEqualTo("Name", nameCategory);

        try {
            return query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Lists.newArrayList();
    }

    private List<ParseObject> fetchBy(String className, List<String> ingredients) {
        ParseQuery<ParseObject> query = new ParseQuery<>(className);

        try {
            for (String ingredient : ingredients) {
                query.whereEqualTo("Name", ingredient);
            }

            return query.find();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Lists.newArrayList();
    }

    public List<CommandToRecipe> getCommandToRecipesBy(String recipeId)
    {
        ParseQuery<ParseObject> query = new ParseQuery<>("CommandToRecipe");

        try {

            query.whereEqualTo("RecipeId", recipeId);

            return Lists.newArrayList(Collections2.transform(query.find(), new Function<ParseObject, CommandToRecipe>()
            {
                @Override
                public CommandToRecipe apply(ParseObject input)
                {
                    CommandToRecipe commandToRecipe = new CommandToRecipe();

                    commandToRecipe.setId(input.getString("objectId"));
                    commandToRecipe.setName(input.getString("Name"));
                    commandToRecipe.setSubject(input.getString("Subject"));
                    commandToRecipe.setCommand(input.getString("Command"));
                    commandToRecipe.setRecipeId(input.getString("RecipeId"));

                    return commandToRecipe;
                }
            }));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return Lists.newArrayList();
    }
}
