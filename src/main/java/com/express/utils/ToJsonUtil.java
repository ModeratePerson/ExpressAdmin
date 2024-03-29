package com.express.utils;

import com.google.gson.Gson;

public class ToJsonUtil {
    private static final Gson gson = new Gson();
    public static String toJson(Object object) {
        return gson.toJson(object);
    }
}
