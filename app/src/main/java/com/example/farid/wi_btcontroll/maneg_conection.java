package com.example.farid.wi_btcontroll;

import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class maneg_conection {

    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;
    private byte[] mmBuffer;
    String data="hello bt";
    int numBytes;
    TextView textView;
    // mmBuffer store for the stream
    String TAG="TAG";
    Activity activity;
    public maneg_conection(BluetoothSocket socket, Activity activity1,TextView textView1) {
        mmSocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        activity=activity1;
         textView=textView1;
        textView.setText("test for conectin");
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
       String tes="hello hoe r u";
        write(tes.getBytes());

    }
    public void run() {

        mmBuffer = new byte[1024];

; // bytes returned from read()

        // Keep listening to the InputStream until an exception occurs.
        while (true) {
            try {
                // Read from the InputStream.
                numBytes = mmInStream.read(mmBuffer);
                data=new String(mmBuffer,0,numBytes);

                Log.v("fartes",data);

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





    // Call this from the main activity to send data to the remote device.
    public void write(byte[] bytes) {
        try {
            mmOutStream.write(bytes);

            // Share the sent message with the UI activity.

        } catch (IOException e) {
            Log.e(TAG, "Error occurred when sending data", e);
        }
    }

    public BluetoothSocket getMmSocket() {
        return mmSocket;
    }

    // Call this method from the main activity to shut down the connection.
    public void cancel() {
        try {
            mmSocket.close();
            mmInStream.close();
            mmOutStream.close();
        } catch (IOException e) {
            Log.e(TAG, "Could not close the connect socket", e);
        }
    }

}
