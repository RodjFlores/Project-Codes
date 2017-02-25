package com.example.halper.listlab;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SaveFile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_file);
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
    public void SaveFile(View view){

        StringList the_list;
        EditText et2;
        String ad;



        et2 = (EditText) findViewById(R.id.filename);
        ad = (et2.getText().toString());

        the_list = StringList.getInstance();
        try {
            File out = new File(getExternalFilesDir(null), ad);
            FileWriter fw = new FileWriter(out);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            for (List l : the_list){
                for(Object x : l){
                    pw.write(x.toString()+"\n");
                }

            }
            Toast.makeText(SaveFile.this,
                    "SAVED",
                    Toast.LENGTH_SHORT).show();
            pw.close();
        }
        catch(IOException e){
            Toast.makeText(SaveFile.this,
                    "Failed",
                    Toast.LENGTH_SHORT).show();
        }

    }
}
