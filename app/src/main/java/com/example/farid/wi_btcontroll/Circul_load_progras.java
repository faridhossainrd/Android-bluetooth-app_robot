package com.example.farid.wi_btcontroll;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

public class Circul_load_progras {


    Activity activity;
    int r=0;
    public boolean  start_stop=false;
    ProgressBar progressBar;
public  int c=0;
    public void start() throws InterruptedException {




        start_stop=false;
        start_stop=true;
        Thread.sleep(100);



        progressBar.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (start_stop) {

                        try {

                            activity.runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    if (r == 359)
                                        r = 0;
                                    r++;
                                    progressBar.setRotation(r);
                                }
                            });
                            Thread.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                }



            }
        }).start();
    }

    public void stop()
    {
        start_stop=false;
        progressBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
    }
    public Circul_load_progras(ProgressBar progressBar, Activity activity)
    {
        this.activity=activity;
        this.progressBar=progressBar;
    }


}





