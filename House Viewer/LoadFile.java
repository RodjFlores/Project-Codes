package com.example.halper.listlab;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class LoadFile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_file);
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

    public void LoadFile(View view){

        EditText et1;
        String urlName;

        List<Object> h = new ArrayList<>();
        List<Object> h1 = new ArrayList<>();
        List<Object> h2= new ArrayList<>();
        List<Object> h3 = new ArrayList<>();
        List<Object> h4 = new ArrayList<>();
        List<Object> h5 = new ArrayList<>();
        StringList the_list;
        the_list = StringList.getInstance();



        et1 = (EditText) findViewById(R.id.edit_url);
        urlName = et1.getText().toString();

        try {

            URL file_url = new URL(urlName);
            Scanner fsc = new Scanner(file_url.openStream());

            the_list.clear();


            int c = the_list.size();

            String st, city, u;
            int year, sq;
            double price, tax;
            while (fsc.hasNext()) {

                st = fsc.nextLine(); // WHILE loops adds each object in list
                city = fsc.nextLine();
                price = fsc.nextDouble();
                fsc.nextLine();
                year = fsc.nextInt();
                fsc.nextLine();
                tax = fsc.nextDouble();
                fsc.nextLine();
                sq = fsc.nextInt();
                fsc.nextLine();
                u = fsc.nextLine();
                switch (c) {    // Made different lists, as I could not manipulate the data any other way.
                    case 1:
                        h1.addAll(Arrays.asList(st, city, price, year, tax, sq, u));
                        the_list.addLast(h1);
                        Toast.makeText(LoadFile.this,
                                h1.get(0).toString() + c,
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        h2.addAll(Arrays.asList(st, city, price, year, tax, sq, u));
                        the_list.addLast(h2);
                        Toast.makeText(LoadFile.this,
                                h2.get(0).toString() + c,
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        h3.addAll(Arrays.asList(st, city, price, year, tax, sq, u));
                        the_list.addLast(h3);
                        Toast.makeText(LoadFile.this,
                                h3.get(0).toString() + c,
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        h4.addAll(Arrays.asList(st, city, price, year, tax, sq, u));
                        the_list.addLast(h4);
                        Toast.makeText(LoadFile.this,
                                h4.get(0).toString() + c,
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        h5.addAll(Arrays.asList(st, city, price, year, tax, sq, u));
                        the_list.addLast(h5);
                        Toast.makeText(LoadFile.this,
                                h5.get(0).toString() + c,
                                Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        h.addAll(Arrays.asList(st, city, price, year, tax, sq, u));
                        the_list.addLast(h);

                        Toast.makeText(LoadFile.this,
                                h.get(0).toString() + c,
                                Toast.LENGTH_SHORT).show();

                }
                c += 1;
            }
            fsc.close();
        }
        catch (IOException e){
            Toast.makeText(LoadFile.this,
                    "Error Reading File!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
