package com.example.halper.qaclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CommunicateActivity extends AppCompatActivity {

    public static Socket socket = null;
    public static PrintWriter out = null;
    public static Scanner in = null;
    public static TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicate);
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

        int port;
        String hostname;

        // Get the hostname from the intent

        Intent intent = getIntent();
        hostname = intent.getStringExtra(MainActivity.HOST_NAME);

        // Get the port from the intent.  Default port is 4000

        port = intent.getIntExtra(MainActivity.PORT, 4000);

        // get a handle on the TextView for displaying the status

        tv = (TextView) findViewById(R.id.text_answer);

        // Try to open the connection to the server

        try
        {
            socket = new Socket(hostname, port);

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(new InputStreamReader(socket.getInputStream()));

            tv.setText("Connected to server.");
        }
        catch (IOException e)  // socket problems
        {
            tv.setText("Problem: " + e.toString());
            socket = null;
        }

    } // end onCreate

    public void sendQuestion(View view)
    {
        EditText et = (EditText)findViewById(R.id.edit_question);
        EditText et2 = (EditText)findViewById(R.id.edit_question2);
        EditText et3 = (EditText)findViewById(R.id.edit_question3);
        //EditText et4 = (EditText)findViewById(R.id.edit_question4);


        String user_question = et.getText().toString();
        String user_question2 = et2.getText().toString();
        String user_question3 = et3.getText().toString();
        //String user_question4 = et4.getText().toString();

        String answer;
        boolean finished = false;

        // are we connected?

        if(socket == null)
        {
            tv.setText("Not connected.");
        }

        else
        {

            // get the question to send to the server (place it in "user_question")

            // if string is "quit", we're finished; let the
            // server know by sending it "quit".  Also, don't forget
            // to "raise the flag."  Otherwise, send the question
            // and get a response

            if ( user_question == "quit" )
            {
                finished = true;
                out.println(user_question);

            }

            else
            {
                // send the question to server

                out.println(user_question);
                out.println(user_question2);
                out.println(user_question3);
                //out.println(user_question4);

                answer = in.nextLine();


                tv.setText("Answer: " + answer);

            }

            // if we're finished, close the connection

            if(finished)
            {
                try
                {
                    out.close();
                    in.close();
                    socket.close();
                    socket = null;

                    tv.setText("Finished.  Connection closed.");
                }
                catch (IOException e)  // socket problems
                {
                    tv.setText("Problem: " + e.toString());
                }

            }

        }

    } // end sendQuestion

} // end CommunicateActivity
