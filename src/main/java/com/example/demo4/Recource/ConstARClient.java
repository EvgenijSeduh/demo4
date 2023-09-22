package com.example.demo4.Recource;

import com.example.demo4.Recource.Config;

public class ConstARClient extends Config {
    protected static final String AUTORIZATIONS_TABLE = "Autorizations";
    protected static final String AUTORIZATIONS_ID = "id";
    protected static final String AUTORIZATIONS_IDUSER = "id_clients";
    protected static final String AUTORIZATIONS_LOGIN = "login";
    protected static final String AUTORIZATIONS_PASSWORD = "password";

    protected static final String USER_TABLE = "Clients";
    protected static final String USER_ID = "id";
    protected static final String USER_IDPASSPORT = "id_passports";
    protected static final String USER_NAME = "name";
    protected static final String USER_COUNTRY = "country";
    protected static final String USER_CITY = "city";
    protected static final String USER_STREET = "street";
    protected static final String USER_HOME = "home";

    protected static final String PASSPORT_TABLE = "Passports";
    protected static final String PASSPORT_ID = "id";
    protected static final String PASSPORT_SERIES = "series";
    protected static final String PASSPORT_NUMBER = "number";
}
