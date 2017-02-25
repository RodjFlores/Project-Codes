package com.example.bigrodpussymaster.lab2;


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

import java.net.URL;
import java.util.ArrayList;
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
    public void displayEmployees(View view)throws java.io.IOException{
        EditText et1;
        String urlName;
        TextView tv;

        tv = (TextView) findViewById(R.id.text_main);
        et1 = (EditText) findViewById(R.id.edit_file);
        urlName = et1.getText().toString();

        URL file_url = new URL(urlName);
        Scanner fsc = new Scanner(file_url.openStream());

        ArrayList<Employee> employeeList = new ArrayList();
        int employeeCounter= 0;
        double YOS = 0;
        Double hourlyWagesXHours =0.0;
        int totalHours=0;
        while(fsc.hasNext()){
            //saves each 6 lines as variables
            String name = fsc.nextLine();
            String id = fsc.nextLine();
            Double hourlyWage = fsc.nextDouble();
            fsc.nextLine();
            int hours = fsc.nextInt();
            fsc.nextLine();
            String office = fsc.nextLine();
            double yrsOfService = fsc.nextInt();
            fsc.nextLine();
            //Applies data to create a new Employee object and appends it to the Employee Array List
            Employee employee = new Employee(name,id,hourlyWage,hours,office,yrsOfService);
            employeeList.add(employee);
            //Keeps tabs of extra data
            employeeCounter++;
            YOS+=yrsOfService;
            hourlyWagesXHours+=(hourlyWage*hours);
            totalHours+=hours;
        }
        //Prints employees
        for(Employee x:employeeList){
            tv.append("\n"+x.getAll());
        }
        tv.append("\nAverage Years of Service: "+(String.format("%.1f",(YOS/employeeCounter))));

        tv.append("\nWeighted average of the hourly wage per week: $" + String.format("%.2f",hourlyWagesXHours/totalHours));
        fsc.close();
    }




}