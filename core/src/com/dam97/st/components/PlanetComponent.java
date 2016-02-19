package com.dam97.st.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by dam97 on 2016-02-07.
 */
public class PlanetComponent implements Component,Pool.Poolable {
    String name = "";
    float size = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    @Override
    public void reset() {
        size = 0;
        name = "";
    }
}
