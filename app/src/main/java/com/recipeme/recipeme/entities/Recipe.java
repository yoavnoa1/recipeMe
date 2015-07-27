package com.recipeme.recipeme.entities;

public class Recipe
{
    private String id;
    private String name;
    private String preparation;
    private String ingredient;
    private String time;
    private int level;
    private String kosher;
    private byte[] picture;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPreparation()
    {
        return preparation;
    }

    public void setPreparation(String preparation)
    {
        this.preparation = preparation;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public String getKosher()
    {
        return kosher;
    }

    public void setKosher(String kosher)
    {
        this.kosher = kosher;
    }

    public byte[] getPicture()
    {
        return picture;
    }

    public void setPicture(byte[] picture)
    {
        this.picture = picture;
    }

    public String getIngredient()
    {
        return ingredient;
    }

    public void setIngredient(String ingredient)
    {
        this.ingredient = ingredient;
    }
}
