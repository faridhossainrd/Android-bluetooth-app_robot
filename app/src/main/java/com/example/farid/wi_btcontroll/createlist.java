package com.example.farid.wi_btcontroll;

public class createlist {


    public void setName(String name) {
        this.name = name;
    }

    String name;

    public void setAddress(String address) {
        this.address = address;
    }

    String address;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    createlist(String name, String address)
    {
        this.name=name;
        this.address=address;

    }


}
