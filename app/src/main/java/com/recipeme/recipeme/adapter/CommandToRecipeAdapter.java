package com.recipeme.recipeme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.recipeme.recipeme.R;
import com.recipeme.recipeme.entities.CommandToRecipe;

import java.util.List;

public class CommandToRecipeAdapter extends ArrayAdapter<CommandToRecipe>
{
    private final Context context;
    private final List<CommandToRecipe> commandToRecipeList;

    public CommandToRecipeAdapter(Context context, List<CommandToRecipe> commandToRecipeList)
    {
        super(context, R.layout.command_row_layout, commandToRecipeList);
        this.context = context;
        this.commandToRecipeList = commandToRecipeList;
    }

    @Override
    public int getCount()
    {
        return commandToRecipeList.size();
    }

    @Override
    public CommandToRecipe getItem(int position)
    {
        return commandToRecipeList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;

        if (convertView == null)
        {
            vi = LayoutInflater.from(context).inflate(R.layout.command_row_layout, null);
        }
        ((TextView) vi.findViewById(R.id.commandTextView)).setText(commandToRecipeList.get(position).getName());

        return vi;
    }
}
