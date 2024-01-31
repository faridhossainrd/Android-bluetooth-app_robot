package com.example.farid.wi_btcontroll;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /// ..........................golobal variable...... start..........
    Bluetooth bluetooth;
    Switch switchon;
    ProgressBar progressBar;
    ListView listView,listView1;
    TextView namebt,refres;
    CoustomArrayAdabder coustomArrayAdabder;
    Circul_load_progras circul_load_progras;
    /// ..........................mainmathoth...... start..........
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        initcom();

    }

    void initcom()
    {


        switchon=(Switch)findViewById(R.id.switchb);
        listView=(ListView)findViewById(R.id.btlistpart);
        listView1=(ListView)findViewById(R.id.btlistunpart);
        progressBar=(ProgressBar)findViewById(R.id.cirprog);
        namebt=(TextView)findViewById(R.id.cntnam);
        refres=(TextView)findViewById(R.id.refres);

        circul_load_progras=new Circul_load_progras(progressBar,MainActivity.this);
        bluetooth=new Bluetooth(MainActivity.this);



        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(switchon.isChecked())
                {
                    progrestime();
                    if(BTcon()){
                        Toast.makeText(MainActivity.this,"Refreshing...." , Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"sorry ...." , Toast.LENGTH_SHORT).show();

                    }

                }else
                {
                    Toast.makeText(MainActivity.this,"Pleas Turn on bluetooth ...." , Toast.LENGTH_SHORT).show();

                }


            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                bluetooth.mBluetoothAdapter.cancelDiscovery();

               alart_masses alart_masses1=new alart_masses(MainActivity.this,bluetooth.getBTpartdeveice.get(i));

            }
        });
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                bluetooth.mBluetoothAdapter.cancelDiscovery();
                alart_masses alart_masses1=new alart_masses(MainActivity.this,bluetooth.getBTunpartdeveice.get(i));
            }
        });


        switchon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchon.isChecked()) {

               progrestime();

                if(BTcon()){
                   switchon.setText("On");
                   switchon.setTextColor(Color.BLUE);
                }
                else
                {
                    switchon.setText("Off");
                    switchon.setTextColor(Color.RED);
                    switchon.setChecked(false);
                }

                }
                else {
                if(bluetooth.disconectBT())
                {
                    bluetooth.getCoustomArrayAdabder_bunded.notifyDataSetChanged();
                    bluetooth.getCoustomArrayAdabder_unbunded.notifyDataSetChanged();
                    switchon.setText("Off");
                    switchon.setTextColor(Color.RED);

                }
                else {
                    Toast.makeText(MainActivity.this, "Sorry disconectBT problem", Toast.LENGTH_SHORT).show();
                }

                }
            }

        });

        if(bluetooth.mBluetoothAdapter.isEnabled())
        {
            if( BTcon())
            {
                progrestime();
                switchon.setChecked(true);
            }
        }


    }




    void progrestime()
    {
        try {
            circul_load_progras.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CountDownTimer countDownTimer=new CountDownTimer(15000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
               final int c= (int) (millisUntilFinished / 1000);

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        refres.setText("Refresh    "+String.valueOf(c)+" ");
                    }
                });
            }

            @Override
            public void onFinish() {
                refres.setText("Refresh    0 ");
                circul_load_progras.stop();
            }
        }.start();

    }

boolean BTcon() {

   if(bluetooth.initBT())
   {
       listView.setAdapter(bluetooth.getCoustomArrayAdabder_bunded);
       listView1.setAdapter(bluetooth.getCoustomArrayAdabder_unbunded);
       return true;
   }
  else
      return false;


}

}

