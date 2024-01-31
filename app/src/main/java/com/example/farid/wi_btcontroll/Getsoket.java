package com.example.farid.wi_btcontroll;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothSocket;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

@SuppressLint("ParcelCreator")
public
class Getsoket implements Parcelable {

    protected Getsoket(Parcel in) {
    }

    public static final Creator<Getsoket> CREATOR = new Creator<Getsoket>() {
        @Override
        public Getsoket createFromParcel(Parcel in) {
            return new Getsoket(in);
        }

        @Override
        public Getsoket[] newArray(int size) {
            return new Getsoket[size];
        }
    };

    public BluetoothSocket getSocket() {
        return socket;
    }

    BluetoothSocket socket;
    Getsoket(BluetoothSocket socket)
    {
        this.socket=socket;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
