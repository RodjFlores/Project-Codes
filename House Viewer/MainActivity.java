package com.example.halper.listlab;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.String;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import com.facebook.drawee.view.SimpleDraweeView;
/////////////////////////////////////////////////////
//
// Rod Flores
// IT 114 Sec 003
// Dr. Halper
// App Project 1
// 11/10/16
// This app is designed to view data of house and
// be able to manipulate said data. Viewing images,
// adding/removing items, and listing details.
//
/////////////////////////////////////////////////////

public class MainActivity extends AppCompatActivity {

    public static TextView tv;


    DecimalFormat df = new DecimalFormat("#.00");
    List<Object> h1 = new ArrayList<>();
    List<Object> h2 = new ArrayList<>();
    List<Object> h3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        StringList the_list;

        h1.addAll(Arrays.asList("1 First St","First City",100000.99,2001,1000.50,10000,"https://i.imgur.com/KbwPb6Yb.jpg"));
        h2.addAll(Arrays.asList("2 Second St","Second City",200000.99,2002,2000.50,20000,"https://s-media-cache-ak0.pinimg.com/736x/1e/1e/ca/1e1eca7542a9ab50af82b6fc19594fc4.jpg"));
        h3.addAll(Arrays.asList("3 Third St","Third City",300000.99,2003,3000.50,30000,"http://www.sissetonmuseums.org/media/pages/10.jpg"));

        // set the reference to the "main" textview object so
        // we do not have to retrieve it in every method below

        tv = (TextView) findViewById(R.id.text_main);

        // create/access the list of strings

        the_list = StringList.getInstance();

        // put some strings on the list (if the list is empty).  Note that the
        // "new" list might not be empty due to a restart of the app

        if(the_list.isEmpty())
        {
            the_list.add(the_list.size(), h1);
            the_list.add(the_list.size(), h2);
            the_list.add(the_list.size(), h3);
        }

    } // end onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onOption1(MenuItem i)
    {
        StringList the_list;

        tv.setText("NORMAL ORDER" + '\n'+'\n');

        the_list = StringList.getInstance();

        for(List l : the_list) {
            tv.append(l.get(0).toString()+", "+l.get(1).toString()+", "+l.get(5).toString()+" sqft"+"\n");
            tv.append("------------------------------------"+'\n');

        }
    } // end onOption1
    public void onOption2(MenuItem i)
    {
        StringList the_list;

        tv.setText("REVERSE ORDER" + '\n'+'\n');

        the_list = StringList.getInstance();
        Collections.reverse(the_list);

        for(List l : the_list) {
            tv.append(l.get(0).toString()+", "+l.get(1).toString()+", "+l.get(5).toString()+" sqft"+"\n");
            tv.append("------------------------------------"+'\n');

        }
        Collections.reverse(the_list);
    } // end onOption2

    public void onOption3(MenuItem i) // ADD HOUSE
    {
        startActivity(new Intent(this, AddItemActivity.class));
    }//o3

    public void onOption4(MenuItem i)//HOUSE DETAILS
    {
        startActivity(new Intent(this, ViewHouse.class));
    }//o4

    public void onOption5(MenuItem i) //REMOVE HOUSE
    {
        startActivity(new Intent(this, RemoveItem.class));
    } // end onOption5

    public void onOption6(MenuItem i)// SHOW MEDIAN
    {

        StringList the_list;
        the_list = StringList.getInstance();
        tv.setText("Median House Price: " + '\n');
        List temp = new ArrayList();

        for(List l : the_list) {
            temp.add(l.get(2));
        }
        Collections.sort(temp);
        for(Object x : temp){
            tv.append(x.toString()+'\n');
        }

        tv.append("---------------------"+'\n');

        Collections.sort(temp);

        if(temp.size()%2==0){       //FORMULA for finding the median
            int x1 = temp.size()/2;
            int x2;

            if(temp.size() == 2){
                x2 = 0;
            }
            else{
                x2 = x1 - 1;
            }

            double median = (Double.parseDouble(temp.get(x1).toString()) + Double.parseDouble(temp.get(x2).toString()))/2;
            String med = df.format(median);
            tv.append("$" + med);
        }
        else{
            double mid = (temp.size()/2);
            int med = (int) mid;

            tv.append(temp.get(med).toString());
        }


    }//O6

    public void onOption7(MenuItem i) //AVERAGE TAX
    {
        StringList the_list;
        the_list = StringList.getInstance();
        tv.setText("Average Property Tax: " + '\n');
        List<Double> temp = new ArrayList<>();

        for(List l : the_list){
            temp.add(Double.parseDouble(l.get(4).toString()));
        }
        for(Object x : temp){
            tv.append(x.toString()+'\n');
        }

        double add = 0;
        for( double x : temp){
            add+=x;
        }


        double ave = add/temp.size();
        String a = df.format(ave);

        tv.append("---------------------"+'\n');
        tv.append("$" + a);

    } // end onOption7
    public void onOption8(MenuItem i) //LEAST EXPENSIVE
    {
        StringList the_list;
        the_list = StringList.getInstance();
        tv.setText("Least Expensive House: " + '\n'+'\n');
        List<Double> temp = new ArrayList<>();



        for( List l : the_list){    //GETS LOWEST VAL
            temp.add(Double.parseDouble(l.get(2).toString()));
        }
        double y = 0;
        for( double x : temp){
            if(y==0){
                y = x;
            }
            if(x<y){
                y=x;
            }
        }
        int r = temp.indexOf(y); // LOWEST VAL FINDER ENDS HERE


        tv.append("Checking if correct..."+the_list.get(r).toString()+ '\n'+'\n');

        int count = 0;
        for(List l: the_list){
            if(count == r){
                tv.append(l.get(0).toString()+ '\n');
                tv.append(l.get(1).toString()+ '\n');
                tv.append("Price: $"+l.get(2).toString()+ '\n');
                tv.append(l.get(5).toString()+" SqFt"+ '\n');
            }
            count+=1;
        }

    } //end O8

    public void onOption9(MenuItem i)//HOUSE DETAILS
    {
        startActivity(new Intent(this, LoadFile.class));
    }//o9

    public void onOption10(MenuItem i)//HOUSE DETAILS
    {
        startActivity(new Intent(this, SaveFile.class));
    }//10

} // end MainActivity
