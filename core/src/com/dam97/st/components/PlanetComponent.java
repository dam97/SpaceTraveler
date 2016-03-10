package com.dam97.st.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;
import com.dam97.st.entities.PlanetType;

/**
 * Created by dam97 on 2016-02-07.
 */
public class PlanetComponent implements Component,Pool.Poolable {
    String name = "";
    float size = 0;
    PlanetType type = PlanetType.NONE;

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

    public PlanetType getType() {
        return type;
    }

    public void setType(PlanetType type) {
        this.type = type;
    }

    @Override
    public void reset() {
        size = 0;
        name = "";
        type = PlanetType.NONE;
    }
}
