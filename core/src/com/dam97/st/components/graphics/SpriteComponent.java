package com.dam97.st.components.graphics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by dam97 on 2016-02-03.
 */
public class SpriteComponent implements Component, Pool.Poolable {
    public Sprite sprite;

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void reset() {
        sprite = new Sprite();
    }
}
