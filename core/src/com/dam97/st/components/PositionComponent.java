package com.dam97.st.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by dam97 on 2016-02-19.
 */
public class PositionComponent implements Component, Pool.Poolable{
    Vector3 position = new Vector3();

    @Override
    public void reset() {
        position = new Vector3();
    }
}
