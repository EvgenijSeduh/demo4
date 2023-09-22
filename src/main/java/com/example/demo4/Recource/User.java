package com.example.demo4.Recource;

import com.example.demo4.Recource.Requirements;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User extends Requirements {
    private final String HASH_ALGORITHM = "SHA-256";
    private final String SALT = "secret_phrase";
    private String login;
    private byte[] password;
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

    public byte[] getPassword() {
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

    public boolean setInfo(String login, String password, String name, String country, String city, String street, String home, String seriesPass, String numberPass) throws NoSuchAlgorithmException {
        if(
            setLogin(login) &&
            setPassword(password) &&
            setName(name) &&
            setCountry(country) &&
            setCity(city) &&
            setStreet(street) &&
            setHome(home) &&
            setNumberPass(numberPass) &&
            setSeriesPass(seriesPass))
            return true;
        else
            return false;

    }

    public boolean setLogin(String login) {
        if (login.matches(loginRegular)){
            this.login = login;
            return true;
        }
        else
            return false;
    }

    public boolean setPassword(String password) throws NoSuchAlgorithmException {
        if(password.matches(passwordRegular)) {
            this.password = hash(password);
            return true;
        }
        else
         return false;
    }

    public boolean setPassword(byte[] password){
        this.password = password;
        return true;
    }

    public boolean setName(String name) {
        if(name.matches(nameRegular)) {
            this.name = name;
            return true;
        }
        else
            return false;
    }

    public boolean setCountry(String country) {
        if(country.matches(countryRegular)){
            this.country = country;
            return true;
        }
        else
            return false;
    }

    public boolean setCity(String city) {
        if(city.matches(cityRegular)) {
            this.city = city;
            return true;
        }
        else
            return false;
    }

    public boolean setStreet(String street) {
        if (street.matches(streetRegular)) {
            this.street = street;
            return true;
        }
        else
            return false;

    }

    public boolean setHome(String home) {
        if(home.matches(homeRegular)) {
            this.home = home;
            return true;
        }
        else
            return false;
    }

    public boolean setSeriesPass(String seriesPass) {
        if(seriesPass.matches(seriesPassRegular)){
            this.seriesPass = seriesPass;
            return true;
        }
        else
            return false;
    }

    public boolean setNumberPass(String numberPass) {
        if(numberPass.matches(numberPassRegular)) {
            this.numberPass = numberPass;
            return true;
        }
        else
            return false;
    }

    public boolean isFill(){
        if(!login.isEmpty() && password.length!=0 && !name.isEmpty() && !country.isEmpty() && !city.isEmpty() && !street.isEmpty() && !home.isEmpty() && !numberPass.isEmpty() && !seriesPass.isEmpty())
            return true;
        else
            return false;
    }

    public boolean isFillAutorization(){
        if(!login.isEmpty() && password.length!=0)
            return true;
        else
            return false;
    }

    public byte[] hash(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
        byte[] hashPassword = digest.digest((password + SALT).getBytes());
        return hashPassword;
    }
}
