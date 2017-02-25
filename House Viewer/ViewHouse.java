package com.example.halper.listlab;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ViewHouse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_house);
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

    public void ViewHouse(View view) {

        TextView tv;
        EditText et2;
        String ad;
        StringList the_list;

        tv = (TextView) findViewById(R.id.houseview) ;
        et2 = (EditText) findViewById(R.id.view_house);
        ad = (et2.getText().toString());

        the_list = StringList.getInstance();

        int year =2016;
        int c = 0;
        for (List l : the_list) {

            if (l.get(0).equals(ad)) {

                int birth = (int) l.get(3);
                int age = year-birth;

                tv.setText("");

                tv.append( l.get(0).toString() + '\n');
                tv.append( l.get(1).toString() + '\n');
                tv.append("Price: $" + l.get(2).toString() + '\n');
                tv.append("Year Built: " + l.get(3).toString() + '\n');
                tv.append("Taxes: $" + l.get(4).toString() + '\n');
                tv.append(l.get(5).toString() + " SqFt." + '\n');
                if(age==0){
                    tv.append("New Construction"+"\n");
                }
                else {
                    tv.append("Age: "+age+'\n');
                }
                tv.append("\n" +  '\n');

                Uri uri = Uri.parse(l.get(6).toString());
                SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.image_area);
                draweeView.setImageURI(uri);

                break;
            }

            c += 1;
            if((c)==the_list.size()){
                Toast.makeText(ViewHouse.this,
                        "ERROR: ADDRESS NOT FOUND",
                        Toast.LENGTH_SHORT).show();
            }

        }


    }

}
