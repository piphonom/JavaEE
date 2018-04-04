package ru.otus.rik.service.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;

public class JsonBinder<T> {

    private Class<T> type;

    public JsonBinder(Class<T> type) {
        this.type = type;
    }

    private static final Gson jsonBuilder = new GsonBuilder().setPrettyPrinting().create();

    public String toJson(T object) {
        return jsonBuilder.toJson(object);
    }

    public T fromJson(Reader json) {
        return jsonBuilder.fromJson(json, type);
    }
}
