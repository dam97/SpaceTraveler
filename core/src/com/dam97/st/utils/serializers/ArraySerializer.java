package com.dam97.st.utils.serializers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.uwsoft.editor.renderer.Overlap2D;

/**
 * Created by dam97 on 2016-03-04.
 */
public class ArraySerializer extends Serializer<Array> {
    {
        setAcceptsNull(true);
    }

    private Class genericType;

    public void setGenerics(Kryo kryo, Class[] generics) {
        if (kryo.isFinal(generics[0])) genericType = generics[0];
    }

    @Override
    public void write(Kryo kryo, Output output, Array array) {
        int length = array.size;
        output.writeInt(length, true);
        if (length == 0) {
            genericType = null;
            return;
        }
        if (genericType != null) {
            Serializer serializer = kryo.getSerializer(genericType);
            genericType = null;
            for (Object element : array)
                kryo.writeObjectOrNull(output, element, serializer);
        } else {
            for (Object element : array)
                kryo.writeClassAndObject(output, element);
        }
    }

    @Override
    public Array read(Kryo kryo, Input input, Class<Array> type) {
        Array array = new Array();
        kryo.reference(array);
        int length = input.readInt(true);
        array.ensureCapacity(length);
        if (genericType != null) {
            Class elementClass = genericType;
            Serializer serializer = kryo.getSerializer(genericType);
            genericType = null;
            for (int i = 0; i < length; i++)
                array.add(kryo.readObjectOrNull(input, elementClass, serializer));
        } else {
            for (int i = 0; i < length; i++)
                array.add(kryo.readClassAndObject(input));
        }
        return array;
    }
}
