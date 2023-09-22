package com.example.demo4.Recource;

public class Requirements {
    protected final String loginRegular = "^[A-Za-z0-9_-]{8,}$";
    protected final String passwordRegular = "^[A-Za-z0-9_-]{8,}$";
    protected final String nameRegular = "^[A-Za-zА-Яа-я]{2,50}$";
    protected final String countryRegular = "^[A-Za-zА-Яа-я]{2,50}$";
    protected final String cityRegular = "^[A-Za-zА-Яа-я]{2,50}$";
    protected final String streetRegular = "^[A-Za-zА-Яа-я]{2,50}$";
    protected final String homeRegular = "^[0-9А-Яа-яЁё\\/]+${1,6}";
    protected final String seriesPassRegular = "^[0-9]{4}$";
    protected final String numberPassRegular = "^[0-9]{6}$";

}
