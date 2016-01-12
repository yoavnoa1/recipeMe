package com.recipeme.recipeme.entities;

import java.io.Serializable;
import java.util.Arrays;

public class Recipe implements Serializable
{
    private String id;
    private String name;
    private String preparation;
    private String ingredient;
    private String time;
    private String level;
    private String kosher;
    private byte[] picture;
    private Integer lessIngredient;

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

    public String getLevel()
    {
        return level;
    }

    public void setLevel(String level)
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

    public Integer getLessIngredient() {
        return lessIngredient;
    }

    public void setLessIngredient(Integer lessIngredient) {
        this.lessIngredient = lessIngredient;
    }


    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", preparation='" + preparation + '\'' +
                ", ingredient='" + ingredient + '\'' +
                ", time='" + time + '\'' +
                ", level='" + level + '\'' +
                ", kosher='" + kosher + '\'' +
                ", picture=" + Arrays.toString(picture) +
                '}';
    }
}
