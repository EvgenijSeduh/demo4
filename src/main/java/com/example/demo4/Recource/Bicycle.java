package com.example.demo4.Recource;

public class Bicycle {
    protected int id;
    protected String name;
    protected String config;
    protected String status;
    protected int price;
    protected String additionalInfo;

    public Bicycle(int id, String name,String config, String status,int price, String additionalInfo){
        this.id = id;
        this.name = name;
        this.config = config;
        this.status = status;
        this.price = price;
        this.additionalInfo = additionalInfo;
    }
    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }
    public String getName() {
        return name;
    }

    public String getConfig() {
        return config;
    }

    public String getStatus() {
        return status;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
