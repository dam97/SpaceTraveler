package com.dam97.st.utils.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.util.UUID;

/**
 * Created by dam97 on 2016-03-03.
 */
public class UUIDSerializer extends Serializer<UUID> {

    @Override
    public void write(Kryo kryo, Output output, UUID object) {
        kryo.writeObject(output, object.toString());
    }

    @Override
    public UUID read(Kryo kryo, Input input, Class<UUID> type) {
        return UUID.fromString(kryo.readObject(input, String.class));
    }
}
