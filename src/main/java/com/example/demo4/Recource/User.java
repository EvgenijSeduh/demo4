package com.example.demo4.Recource;

public class User extends ShortUser{
    protected String address;
    protected String status;

    public User(int id, String name, String passport, String address, String status) {
        super(id, name, passport);
        this.id = id;
        this.name = name;
        this.passport = passport;
        this.address = address;
        this.status = status;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
