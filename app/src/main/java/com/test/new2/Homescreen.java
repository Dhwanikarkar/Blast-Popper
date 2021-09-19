package com.test.new2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Homescreen extends AppCompatActivity
{
    Thread t;
    int count=0;
    ImageView i1,i2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        i1=(ImageView)findViewById(R.id.playbtn);
        i2=(ImageView)findViewById(R.id.logo);
        startsplash();
    }

    //splash screen
    public void startsplash()
    {
        try
        {
            Animation a=AnimationUtils.loadAnimation(this, R.anim.uptodown);
            Animation b=AnimationUtils.loadAnimation(this, R.anim.downtoup);
            a.reset();
            b.reset();
            i1.startAnimation(b);
            i2.startAnimation(a);
            t= new Thread()
            {
                @Override
                public void run()
                {
                    while(count<6000)
                    {
                        try
                        {
                            sleep(100);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        count+=100;
                    }
                }
            };
            t.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //to playscreen

    public void playscreen(View v)
    {
        Intent i=new Intent(this,Playscreen.class);
        startActivity(i);
    }

}
