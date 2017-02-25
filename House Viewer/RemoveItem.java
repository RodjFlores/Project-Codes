package com.example.halper.listlab;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class RemoveItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_item);
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

    public void removeItem(View view) {

        EditText et2;
        String ad;
        StringList the_list;

        et2 = (EditText) findViewById(R.id.remove_position);
        ad = (et2.getText().toString());

        the_list = StringList.getInstance();


        int c = 0;
        for (List l : the_list) {

            if (l.get(0).equals(ad)) {
                the_list.remove(c);
                Toast.makeText(RemoveItem.this,
                        "Removed House!",
                        Toast.LENGTH_SHORT).show();
                break;
            }
            else{
                Toast.makeText(RemoveItem.this, //Made a nice little counter
                        "Loading ( " + (c+1) + "/"+ the_list.size() + ")" ,
                        Toast.LENGTH_SHORT).show();

            }
            c += 1;
            if((c)==the_list.size()){
                Toast.makeText(RemoveItem.this,
                        "ERROR: ADDRESS NOT FOUND",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}


