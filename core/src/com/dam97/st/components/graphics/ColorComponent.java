package com.dam97.st.components.graphics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by dam97 on 2016-02-07.
 */
public class ColorComponent implements Component, Pool.Poolable{
    Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void reset() {
        color = new Color(0,0,0,1);
    }
}