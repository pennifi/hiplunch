package com.soikea.hiplunch;

public class Constants {

    public static final String[] HIGHLIGHTS = { "pekoni", "olut", "kalja", "pizza", "makkara", "burger", "purilainen" };

    public static final String[] DEFAULT_PROVIDERS = { "bittipannu", "fiilu", "nurkka", "trattoria", "qulkuri", "sodexo-ge" };

    /**
     * If property s not set with -D returns s from env
     */
    protected static String getSystemVariable(String s) {
        return System.getProperty(s) != null ? System.getProperty(s) : System.getenv(s);
    }
}
