package com.example.halper.listlab;

/**
 * Created by halper on 9/7/2016.
 */
import java.util.LinkedList;
import java.util.List;

public final class StringList extends LinkedList<List>
{

    private static StringList instance = null;

    private StringList()
    {
        // Exists only to defeat additional instantiations.
    }

    public static StringList getInstance()
    {
        if(instance == null)
            instance = new StringList();

        return instance;
    }

} // end StringList

