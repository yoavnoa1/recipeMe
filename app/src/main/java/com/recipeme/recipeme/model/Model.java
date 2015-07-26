package com.recipeme.recipeme.model;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Model
{
    public void query()
    {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Recipe");
        try {
            List<ParseObject> parseObjects = query.find();

            if(!parseObjects.isEmpty())
            {
                ParseObject parseObject = parseObjects.get(0);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
