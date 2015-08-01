package com.recipeme.recipeme.entities;

import java.io.Serializable;

public interface Ingredient extends Serializable
{
    String getName();
    void setName(String name);

    String getId();
    void setId(String Id);
}
