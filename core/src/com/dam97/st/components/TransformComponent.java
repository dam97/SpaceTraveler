package com.dam97.st.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by dam97 on 2016-02-07.
 */
public class TransformComponent implements Component, Pool.Poolable {
    final Vector2 position = new Vector2(0f, 0f);
    final Vector2 size = new Vector2(0f, 0f);
    final Vector2 scale = new Vector2(1.0f, 1.0f);
    final Vector2 origin = new Vector2(0.5f, 0.5f);
    int ZIndex = 0;
    float rotation = 0.0f;

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getSize() {
        return size;
    }

    public Vector2 getScale() {
        return scale;
    }

    public Vector2 getOrigin() {
        return origin;
    }

    public int getZIndex() {
        return ZIndex;
    }

    public void setZIndex(int ZIndex) {
        this.ZIndex = ZIndex;
    }

    public float getRotation() {
        return rotation;
    }


    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void setBounds(Rectangle rectangle) {
        this.getPosition().x = rectangle.x + rectangle.width * 0.5f;
        this.getPosition().y = rectangle.y + rectangle.height * 0.5f;
        this.getSize().x = rectangle.width;
        this.getSize().y = rectangle.height;
    }

    public Rectangle getBounds() {
        Rectangle rectangle = new Rectangle(position.x - size.x * 0.5f, position.y - size.y * 0.5f, size.x, size.y);
        return rectangle;
    }


    @Override
    public void reset() {
        position.set(0f,0f);
        size.set(0f,0f);
        scale.set(1f,1f);
        origin.set(0.5f, 0.5f);
        ZIndex = 0;
        rotation = 0.0f;
    }
}
