package com.example.farid.wi_btcontroll;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;

public class Bluetooth {


    public BluetoothAdapter getmBluetoothAdapter() {
        return mBluetoothAdapter;
    }
  public  CoustomArrayAdabder getCoustomArrayAdabder_unbunded;
  public  CoustomArrayAdabder getCoustomArrayAdabder_bunded;

public boolean  pair=false,unpair=false;
    String addressmasch="";

   public  ArrayList<BluetoothDevice> getBTpartdeveice = new ArrayList<BluetoothDevice>();
    public ArrayList<BluetoothDevice> getBTunpartdeveice = new ArrayList<BluetoothDevice>();
    public BluetoothAdapter mBluetoothAdapter;
    Set<BluetoothDevice> getBTBondedDevices;
    Activity context;

   public Bluetooth(Activity context)
    {
        this.context=context;
        getCoustomArrayAdabder_unbunded=new CoustomArrayAdabder(context);
        getCoustomArrayAdabder_bunded=new CoustomArrayAdabder(context);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    }

  public boolean initBT() {

       try {
           mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
           getBTBondedDevices = mBluetoothAdapter.getBondedDevices();

           if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
               Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
               context.startActivityForResult(enableBtIntent, 1);

              /* Intent open=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
               open.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,40);
               context.startActivity(open);*/

               while (!mBluetoothAdapter.isEnabled()) ;
               getBTBondedDevices = mBluetoothAdapter.getBondedDevices();

               getbunded();
               getavailable();

           }
           else {
               getbunded();
               getavailable();
           }

           return true;
       }catch (Exception e)
       {
           return false;
       }

    }

    void getbunded()
    {
        Clear_pairlist();

        for (BluetoothDevice iterator : getBTBondedDevices) {

        getBTpartdeveice.add(iterator);
        getCoustomArrayAdabder_bunded.demo.add(new createlist(iterator.getName(),iterator.getAddress()));
    }
    }
    public boolean disconectBT()
{

    try
    {
        if (mBluetoothAdapter.isDiscovering())
        {
            mBluetoothAdapter.cancelDiscovery();
        }
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
            while (mBluetoothAdapter.isEnabled()) ;
            Clear_pairlist();
            Clear_unpairlist();
        }
        return  true;
    }catch (Exception e)
    {
        return false;
    }


}
    void getavailable()
    {
        addressmasch="";
        if (Build.VERSION.SDK_INT >= 23) {

            if(mBluetoothAdapter.isDiscovering()) {
                mBluetoothAdapter.cancelDiscovery();

                mBluetoothAdapter.startDiscovery();
                checkBTPermissions();
                Clear_unpairlist();


                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                context.registerReceiver(mReceiver, filter);
            }
            if(!mBluetoothAdapter.isDiscovering()) {
                mBluetoothAdapter.startDiscovery();
                checkBTPermissions();
                Clear_unpairlist();


                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                context.registerReceiver(mReceiver, filter);

            }

        }else
        {
            if(mBluetoothAdapter.isDiscovering()) {
                mBluetoothAdapter.cancelDiscovery();

                mBluetoothAdapter.startDiscovery();

                Clear_unpairlist();
                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
               context.registerReceiver(mReceiver, filter);
            }
            if(!mBluetoothAdapter.isDiscovering()) {
                mBluetoothAdapter.startDiscovery();

                Clear_unpairlist();

                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                context.registerReceiver(mReceiver, filter);

            }

        }

    }



    public final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {

                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if(!addressmasch.equals(device.getAddress()))
                {
                    addressmasch=device.getAddress();
                    getBTunpartdeveice.add(device);
                    getCoustomArrayAdabder_unbunded.demo.add( new createlist(device.getName(),device.getAddress()));
                    getCoustomArrayAdabder_unbunded.notifyDataSetChanged();

                }


            }
        }
    };




   public void Clear_unpairlist()
   {
       try {
           getBTunpartdeveice.removeAll(getBTunpartdeveice);
           getCoustomArrayAdabder_unbunded.demo.removeAll( getCoustomArrayAdabder_unbunded.demo);
       }catch (Exception e){

       }

}



    private void checkBTPermissions() {


        int permissionCheck = 0;
        if (Build.VERSION.SDK_INT >= 23) {
            permissionCheck = ContextCompat.checkSelfPermission(context,"Manifest.permission.ACCESS_FINE_LOCATION");
            permissionCheck += ContextCompat.checkSelfPermission(context,"Manifest.permission.ACCESS_COARSE_LOCATION");
            if (permissionCheck != 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ActivityCompat.requestPermissions(context,new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1001); //Any number
                }
            }

        }





    }



    public void Clear_pairlist()
    {
        try {
            getBTpartdeveice.removeAll(getBTpartdeveice);
            getCoustomArrayAdabder_bunded.demo.removeAll( getCoustomArrayAdabder_bunded.demo);
        }catch (Exception e){

        }

    }

}
