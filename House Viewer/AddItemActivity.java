package com.example.halper.listlab;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    // add the item to the list

    public void addItem(View view) {

        StringList the_list;
        List<Object> h = new ArrayList<>();

        EditText st = (EditText) findViewById(R.id.edit_st);
        EditText city = (EditText) findViewById(R.id.edit_city);
        EditText price = (EditText) findViewById(R.id.edit_price);
        EditText yr = (EditText) findViewById(R.id.edit_built);
        EditText tax = (EditText) findViewById(R.id.edit_tax);
        EditText sq = (EditText) findViewById(R.id.edit_sq);
        EditText u = (EditText) findViewById(R.id.edit_url);

        String City,St,U;
        int Yr,Sq;
        double Price,Tax;

        St = st.getText().toString();
        City = city.getText().toString();
        Price = Double.parseDouble(price.getText().toString());
        Yr =Integer.parseInt(yr.getText().toString());
        Tax = Double.parseDouble(tax.getText().toString());
        Sq = Integer.parseInt(sq.getText().toString());
        U = u.getText().toString();

        h.addAll(Arrays.asList(St,City,Price,Yr,Tax,Sq,U));

        // Get the string list instance

        the_list = StringList.getInstance();
        int x = the_list.size();

        // try to put the new item on the list

        try
        {
            the_list.add((x),h);

            Toast.makeText(AddItemActivity.this,
                    "Added New House",
                    Toast.LENGTH_SHORT).show();
        }
        catch(IndexOutOfBoundsException e)
        {
            Toast.makeText(AddItemActivity.this,
                    "Failed to add item to the list",
                    Toast.LENGTH_SHORT).show();

        }

    } // end addItem

} // end AddItemActivity


