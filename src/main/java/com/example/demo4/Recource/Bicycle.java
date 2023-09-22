package com.example.demo4.Recource;

public class Bicycle {
    protected int id;
    protected String name;
    protected String model;
    protected String type;
    protected String numberGear;
    protected String status;
    protected String addressShops;
    protected String dateRents;
    protected int price;

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }
    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public String getNumberGear() {
        return numberGear;
    }

    public String getStatus() {
        return status;
    }

    public String getAddressShops() {
        return addressShops;
    }

    public String getDateRents() {
        return dateRents;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumberGear(String numberGear) {
        this.numberGear = numberGear;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAddressShops(String addressShops) {
        this.addressShops = addressShops;
    }

    public void setDateRents(String dateRents) {
        this.dateRents = dateRents;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
