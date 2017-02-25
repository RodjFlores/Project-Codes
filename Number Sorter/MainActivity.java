package com.example.bigrodpussymaster.lab3;

import android.content.res.AssetManager;
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
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

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
    }

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

    public void myArr(View view) throws java.io.IOException{

        TextView tv;
        String in,out;
        tv = (TextView) findViewById(R.id.text_main);
        EditText et = (EditText)findViewById(R.id.edit_file);
        EditText et2 = (EditText)findViewById(R.id.edit_outfile);
        in = (et.getText().toString());
        out = (et2.getText().toString());

        AssetManager assetManager = getAssets();


        Scanner fsc = new Scanner(assetManager.open(in));

        List<Integer> arr = new ArrayList<>();
        int n = 0;


       try{

            Toast.makeText(MainActivity.this,
                    "Saved",
                    Toast.LENGTH_SHORT).show();

            File outfile = new File(getExternalFilesDir(null), out);
            FileWriter fw = new FileWriter(outfile);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);


            while(fsc.hasNext()){
                arr.add(fsc.nextInt());
                n+=1;
            }

            Collections.sort(arr);
            tv.append("Sorted Array: " + arr + "\n");
            tv.append("Lowest Value: " + arr.get(0).toString() + "\n");
            tv.append("Highest Value: " + arr.get(n-1).toString() + "\n");

            double val = 1;
            double power = (1.0/n);
            for(int x : arr){
                val*=x;
                pw.write(x +"\n");
            }
            double mean = Math.pow(val,power);
            tv.append("Geometric Average: " + mean  + "\n");

           if(arr.size()%2==0){
               int x1 = arr.size()/2;
               int x2;

               if(arr.size() == 2){
                   x2=0;
               }
               else {
                   x2 = x1 -1;
               }

               double med = (arr.get(x1) + arr.get(x2))/2.0;
               tv.append("Median: " + med);

           }

           else{
               int med = arr.size()/2;
               tv.append(arr.get(med).toString() + "\n");
           }
           pw.close();

        }
        catch (IOException e){
            Toast.makeText(MainActivity.this,
                    "Error!",
                    Toast.LENGTH_SHORT).show();
        }

    }
}
