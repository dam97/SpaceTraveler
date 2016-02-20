package com.dam97.st.utils;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

import java.io.BufferedReader;
import java.io.Serializable;

/**
 * Created by dam97 on 2016-02-13.
 */
public class DataLoader {
    private static DataLoader instance = new DataLoader();

    private DataLoader() {
        json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
    }

    public static DataLoader getInstance() {
        return instance;
    }

    Json json;

    public void createSave(Object object, String path) {
        FileHandle handle = Gdx.files.local(path);
        handle.writeString(json.prettyPrint(object), false);
        Gdx.app.log("DataLoader", "File saved in " + path);
    }

    public <T> T loadSave(Class<T> type, String path){
        FileHandle handle = Gdx.files.local(path);
        return (T) json.fromJson(type, handle);
    }
}
