package com.test.new2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Playscreen extends AppCompatActivity
{
    RelativeLayout rl;
    ImageView i1;
    int score;
    Thread t,t2;
    int x1,y1,i;
    float count=0;
    databasehelper mydb;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playscreen);
        mydb=new databasehelper(this,null,null,0);
        rl=(RelativeLayout)findViewById(R.id.rell);
        i1=(ImageView)findViewById(R.id.dot1);
        t1=(TextView)findViewById(R.id.score);


        //blink animation with position changing using thread

            t=new Thread()
        {
            public void run()
            {
                while(count<=1000)
                {
                    try
                    {
                        Thread.sleep(2000);
                        x1=(int) (Math.random()*2000);
                        y1=(int) (Math.random()*900);
                        i1.setY(x1);
                        i1.setX(y1);
                        i1.setVisibility(4);

                        runOnUiThread(new Runnable()
                        {
                            Animation a=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);

                            @Override
                            public void run()
                            {
                                i1.startAnimation(a);
                                count++;
                            }
                        });
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();

        t2=new Thread()
        {
            public void run()
            {
                while(i<=1000)
                {
                    try
                    {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run()
                            {
                                i++;
                            }
                        });
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                }
            }
        };
        t2.start();

    }


    //click event on image
    public void click(View v)
    {
        x1=(int) (Math.random()*2000);
        y1=(int) (Math.random()*900);
        i1.setY(x1);
        i1.setX(y1);
        i1.setVisibility(View.INVISIBLE);
        t1.setText(String.valueOf(score));
        score++;
    }


    //retryscreen
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            boolean b=mydb.insertdata(Integer.parseInt(t1.getText().toString()));
            if(b==true)
            {
                String score=t1.getText().toString();
                Intent i=new Intent(getApplicationContext(),Retryscreen.class);
                i.putExtra("score",score);

                startActivity(i);
            }
            else
            {
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
