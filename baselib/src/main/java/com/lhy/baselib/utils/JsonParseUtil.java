package com.lhy.baselib.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.lhy.baselib.network.HttpBaseBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yeqiu on 2016/6/14.
 */
public class JsonParseUtil {
    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new GsonBuilder().serializeNulls().create();
        }
    }

    public static <T> T parseObject(JSONObject json, Class<T> clazz) {
        T t = null;
        if (gson != null)
            t = gson.fromJson(json.toString(), clazz);
        return t;
    }

    public static <T> T parseString(String json, Class<T> clazz) {
        T t = null;
        if (gson != null)
            t = gson.fromJson(json, clazz);
        return t;
    }


    public static <T> List<T> parseArray(JSONArray json, final Class<T> clazz) {
        List<T> tlist = null;
        if (gson != null) {
            tlist = new ArrayList<>();
            for (int i = 0; i < json.length(); i++) {
                tlist.add(parseObject(json.optJSONObject(i), clazz));
            }
        }
        return tlist;
    }

    public static String toJson(HttpBaseBean clazz) {
        String json = null;
        if (gson != null) {
            json = gson.toJson(clazz);
        }
        return json;
    }

    /**
     * @param s
     * @param clazz
     * @param <T>
     * @return
     * @describe 解析[{id=100, name=最新}, {id=200, name=校园}, {id=300, name=关注}, {id=1, name=娱乐}, {id=2, name=美美哒}, {id=3, name=酷玩}, {id=4, name=游戏}, {id=5, name=二手货}]
     */
    public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
        T[] arr = null;
        if (gson != null) {
            arr = gson.fromJson(s, clazz);
        }
        return Arrays.asList(arr);
    }
}
