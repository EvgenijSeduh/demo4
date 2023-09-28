package com.example.demo4.Recource;

public class ShortUser {
    protected int id;
    protected String name;
    protected String passport;

    public ShortUser(int id, String name, String passport) {
        this.id = id;
        this.name = name;
        this.passport = passport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}
