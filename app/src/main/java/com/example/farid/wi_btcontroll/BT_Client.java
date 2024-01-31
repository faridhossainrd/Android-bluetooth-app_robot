package com.example.farid.wi_btcontroll;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class BT_Client extends Thread  {


    private  InputStream mmInStream;
    private  OutputStream mmOutStream;
    private  BluetoothSocket mmSocket;
    private  BluetoothDevice mmDevice;
    private UUID MY_UUID = UUID.fromString("fdc248aa-1543-47ae-b267-eb6e74429925");
    String TAG="TAG";
    public boolean lisentall=true;
    private byte[] mmBuffer;
    String data="hello bt";
    int numBytes;
    Activity activity;
    TextView textView;

    public BluetoothSocket getocket() {
        return mmSocket;
    }

    public BT_Client(BluetoothDevice device, Activity activity, TextView textView) {

        BluetoothSocket tmp = null;
        mmDevice = device;
        this.activity=activity;
         this.textView=textView;
        try {

            Log.d(TAG,mmDevice.getAddress().toString());
            tmp = mmDevice.createRfcommSocketToServiceRecord(MY_UUID);

        } catch (IOException e) {
            lisentall=false;
        }

        mmSocket = tmp;
    }

    public void run() {
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        try {


                mmSocket.connect();

        } catch (IOException connectException) {
            try {
                lisentall=false;
                mmSocket.close();
            } catch (IOException closeException) {
            }
            return;
        }
if(mmSocket!=null)
{
    try {
        Thread.sleep(100);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    try {
        tmpIn = mmSocket.getInputStream();
    } catch (IOException e) {
        Log.e(TAG, "Error occurred when creating input stream", e);
    }
    try {
        tmpOut = mmSocket.getOutputStream();
    } catch (IOException e) {
        Log.e(TAG, "Error occurred when creating output stream", e);
    }
    mmInStream = tmpIn;
    mmOutStream = tmpOut;
}

        lisentall=false;

        mmBuffer = new byte[1024];
        while (true) {
            try {
                // Read from the InputStream.
                numBytes = mmInStream.read(mmBuffer);
                data = new String(mmBuffer, 0, numBytes);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(data);
                    }
                });
            } catch (IOException e) {
                Log.d(TAG, "Input stream was disconnected", e);
                break;
            }
        }
    }

    public void write(byte[] bytes) {
        try {
            mmOutStream.write(bytes);

        } catch (IOException e) {
            Log.e(TAG, "Error occurred when sending data", e);
        }
    }
    // Closes the client socket and causes the thread to finish.
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) {
        }
    }
}
