package com.example.demo4;

public class User {
    private String login;
    private String password;
    private String name;
    private String country;
    private String city;
    private String street;
    private String home;
    private String numberPass;
    private String seriesPass;

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHome() {
        return home;
    }

    public String getSeriesPass() {
        return seriesPass;
    }

    public String getNuberPass() {
        return numberPass;
    }

    public void setInfo(String login, String password, String name, String country, String city, String street, String home, String seriesPass, String numberPass) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
        this.home = home;
        this.numberPass = numberPass;
        this.seriesPass = seriesPass;

    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public void setSeriesPass(String seriesPass) {
        this.seriesPass = seriesPass;
    }

    public void setNumberPass(String numberPass) {
        this.numberPass = numberPass;
    }

    public boolean isFill(){
        if(!login.isEmpty() && !password.isEmpty() && !name.isEmpty() && !country.isEmpty() && !city.isEmpty() && !street.isEmpty() && !home.isEmpty() && !numberPass.isEmpty() && !seriesPass.isEmpty())
            return true;
        else
            return false;
    }

    public boolean isFillAutorization(){
        if(!login.isEmpty() && !password.isEmpty())
            return true;
        else
            return false;
    }
}
