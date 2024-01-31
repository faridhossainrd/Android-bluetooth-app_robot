package com.example.farid.wi_btcontroll;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class alart_masses {


    String TAG="tag";
    Activity context1;
    public ImageButton clint,server,no;
    TextView textView;
    boolean tr=true;
    LayoutInflater inflater;
    BluetoothDevice device;

    public  alart_masses(final Activity context,BluetoothDevice device1) {
        device=device1;
        this.context1=context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context1);
        inflater = (LayoutInflater) context1.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.alart_msm, null);
        builder.setView(view);
        final AlertDialog aler = builder.create();
        aler.show();

         clint = (ImageButton) view.findViewById(R.id.clint);
        server = (ImageButton) view.findViewById(R.id.server);
        no = (ImageButton) view.findViewById(R.id.nobt);
        textView = (TextView) view.findViewById(R.id.textv);
       textView.setText("Are you want connecting to \n" + device.getName() + " ?");

       clint.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {


                               Intent intent=new Intent(context,Send_Receive.class);
                              intent.putExtra("key1","clint");
                               intent.putExtra("key",device.getAddress());
                               context.startActivity(intent);
                               aler.dismiss();

           }
       });
       server.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                               Intent intent=new Intent(context,Send_Receive.class);
                               intent.putExtra("key1","server");
                               intent.putExtra("key",device.getAddress());
                               context.startActivity(intent);
                               aler.dismiss();

           }
       });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aler.dismiss();
            }
        });

    }
}
