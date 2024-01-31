package com.example.farid.wi_btcontroll;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.UUID;

public class bluetooth_server extends Thread {

    private UUID MY_UUID = UUID.fromString("fdc248aa-1543-47ae-b267-eb6e74429925");

    BluetoothSocket socket = null;
    String TAG="TAG";
    String NAME="WIBTCONTROLL";
    private  InputStream mmInStream;
    private  OutputStream mmOutStream;
    public BluetoothServerSocket getMmServerSocket() {
        return mmServerSocket;
    }
    public BluetoothSocket getSocket() {
        return socket;
    }
   public boolean conting=true;
    private  BluetoothServerSocket mmServerSocket;
    BluetoothAdapter mBluetoothAdapter;
    Activity activity;
    TextView textView;
    private byte[] mmBuffer;
    String data="hello bt";
    int numBytes;
    public bluetooth_server(Activity activity, TextView textView ) {

        this.activity=activity;
        this.textView=textView;
        mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();

        BluetoothServerSocket tmp = null;
        try {

            tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
            Log.d(TAG,"lisning ok");

        } catch (IOException e) {

        }
        mmServerSocket = tmp;
    }

    public void run() {
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        // Keep listening until exception occurs or a socket is returned.
        while (socket == null) {
            try {
                Log.d(TAG,"concting....server");
                socket = mmServerSocket.accept();
                Log.d(TAG,"concting....server......ok");
            } catch (IOException e) {
                Log.d(TAG,"concting....server......fail");
                conting = false;
                try {

                    mmServerSocket.close();
                    socket.close();
                } catch (IOException e1) {
                }

                break;
            }
            conting = false;

        }

        if(socket!=null)
        {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                tmpIn = socket.getInputStream();
            } catch (IOException e) {
                Log.e(TAG, "Error occurred when creating input stream", e);
            }
            try {
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(TAG, "Error occurred when creating output stream", e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }


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

            // Share the sent message with the UI activity.

        } catch (IOException e) {
            Log.e(TAG, "Error occurred when sending data", e);
        }
    }
    public void cancel() {
        try {
            socket.close();
            mmServerSocket.close();
        } catch (IOException e) {
        }
    }
}
