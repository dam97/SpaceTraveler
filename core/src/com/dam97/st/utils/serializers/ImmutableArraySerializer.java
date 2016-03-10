package com.dam97.st.utils.serializers;

import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Created by dam97 on 2016-03-03.
 */
public class ImmutableArraySerializer extends Serializer<ImmutableArray> {
    @Override
    public void write(Kryo kryo, Output output, ImmutableArray object) {
        kryo.writeObject(output, object.toArray());
    }

    @Override
    public ImmutableArray read(Kryo kryo, Input input, Class<ImmutableArray> type) {
        return new ImmutableArray(kryo.readObject(input, Array.class));
    }
}
