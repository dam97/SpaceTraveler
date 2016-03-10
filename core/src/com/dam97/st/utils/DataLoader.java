package com.dam97.st.utils;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.dam97.st.utils.serializers.ArraySerializer;
import com.dam97.st.utils.serializers.ImmutableArraySerializer;
import com.dam97.st.utils.serializers.UUIDSerializer;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by dam97 on 2016-02-13.
 */
public class DataLoader {
    private static DataLoader instance = new DataLoader();

    private DataLoader() {
        json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        kryo = new Kryo();
        kryo.register(UUID.class, new UUIDSerializer());
        kryo.register(Array.class, new ArraySerializer());
        kryo.register(ImmutableArray.class, new ImmutableArraySerializer());
        output = new Output(8);
        input = new Input(8);
    }

    public static DataLoader getInstance() {
        return instance;
    }

    Json json;
    Kryo kryo;
    Input input;
    Output output;

    public void createSave(Object object, String path) {
        FileHandle handle = Gdx.files.local(path);
        Gdx.app.log("DataLoader", "File saved in " + path);
        output.setOutputStream(handle.write(false));
        kryo.writeObject(output, object);
        output.close();
    }

    public <T> T loadSave(Class<T> type, String path){
        FileHandle handle = Gdx.files.local(path);
        input.setInputStream(handle.read());
        T object = kryo.readObject(input, type);
        input.close();
        return object;
    }
}
