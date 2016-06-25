package com.softwaredesign.microbar.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by mac on 16/6/24.
 */
public class GsonUtil {
    private static Gson gson = null;
    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    private GsonUtil() {
    }

    public static <T> T parse(String json, Class<T> T) {
        return gson.fromJson(json, T);
    }
    public static <T> T parseList(String json, Type T) {
        return gson.fromJson(json, T);
    }
}
