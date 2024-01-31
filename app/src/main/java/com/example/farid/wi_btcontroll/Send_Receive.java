package com.example.farid.wi_btcontroll;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class Send_Receive extends AppCompatActivity {


     bluetooth_server bluetooth_server_;
     BT_Client bt_client;
    maneg_conection Manegconet;
   int c=0;
   String s="";
    String s1="";
   boolean count=true;
    BluetoothDevice device1;
    Getsoket getsoket;
    Button button;
    EditText editText;
  public TextView textView;
    Dilogbox dilogbox;
Set<BluetoothDevice> devices;
BluetoothAdapter mBluetoothAdapter;


    @Override
    public void onBackPressed() {

        try
        {
            if(bluetooth_server_.getSocket()!=null);
            bluetooth_server_.getSocket().close();

        }catch (Exception e)
        {

        }  try
        {
            if(bt_client.getocket()!=null);
            bt_client.getocket().close();

        }catch (Exception e)
        {

        }  try
        {
            if(Manegconet.getMmSocket()!=null);
            Manegconet.cancel();

        }catch (Exception e)
        {

        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Send_Receive.this, "every thing is closed", Toast.LENGTH_SHORT).show();
            }
        });
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send__receive);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        count=true;
        button = (Button) findViewById(R.id.sendbt);
        editText = (EditText) findViewById(R.id.sms);
        textView = (TextView) findViewById(R.id.recevtx);
         s=getIntent().getExtras().getString("key1");
         s1=getIntent().getExtras().getString("key");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  String s2 = editText.getText().toString();
                editText.setText("");
                if(s.equals("server"))
                {
                    bluetooth_server_.write(s2.getBytes());
                }
                else if(s.equals("clint"))
                {
                    bt_client.write(s2.getBytes());
                }
            }
        });
        dilogbox=new Dilogbox(Send_Receive.this);
            lodbox();

    }

    void lodbox()
    {
        mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        devices= mBluetoothAdapter.getBondedDevices();
      //  timer();
        new  Thread(new Runnable() {
            @Override
            public void run() {

                    for (BluetoothDevice iterator :devices) {
                        if (iterator.getAddress().equals(s1)) {
                            device1 = iterator;
                        }
                    }
                if(s.equals("server"))
                {
                    try {
                        bluetooth_server_ = new bluetooth_server(Send_Receive.this,textView);
                        bluetooth_server_.start();
                        while (bluetooth_server_.conting);
                        dilogbox.demis();

                    } catch (Exception re){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Send_Receive.this, "socket not canected this here", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                }
                else if(s.equals("clint"))
                {
               if(device1.getAddress().equals(s1)) {
                 try {

                                   bt_client = new BT_Client(device1,Send_Receive.this,textView);
                                    bt_client.start();
                                    while (bt_client.lisentall);
                                    dilogbox.demis();

                                } catch (Exception re) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(Send_Receive.this, "socket not canected this here", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                }
                            }
                            else
               {
                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           Toast.makeText(Send_Receive.this, "device not found", Toast.LENGTH_SHORT).show();
                           dilogbox.demis();
                       }
                   });
               }

            }}
                }).start();



    }


}
