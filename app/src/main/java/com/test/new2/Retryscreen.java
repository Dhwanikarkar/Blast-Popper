package com.test.new2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class Retryscreen extends AppCompatActivity
{
    TextView t1,t2,hs;
    databasehelper mydb;
    Cursor c1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retryscreen);
        t1=(TextView)findViewById(R.id.score);
        t2=(TextView)findViewById(R.id.newbest);
        hs=(TextView)findViewById(R.id.hs);

        Animation a=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink_newbest);
        t2.startAnimation(a);

        mydb=new databasehelper(this,null,null,0);

        Intent i1=getIntent();
        int str=parseInt(i1.getStringExtra("score"));
        t1.setText(String.valueOf(str));

        c1=mydb.showdata();


        if(c1.getCount()==0)
        {
            Toast.makeText(this, "no record found", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(c1.moveToNext())
            {

                    hs.setText(String.valueOf(c1.getString(0)));

            }
        }

    }
    //to playscreen
    public void playscreen(View v)
    {
        Intent i=new Intent(this,Playscreen.class);
        startActivity(i);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Intent i1=new Intent(getApplicationContext(),Homescreen.class);
            startActivity(i1);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
