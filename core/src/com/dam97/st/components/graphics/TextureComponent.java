package com.dam97.st.components.graphics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by dam97 on 2016-02-07.
 */
public class TextureComponent implements Component, Pool.Poolable {
    public TextureRegion textureRegion;

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    @Override
    public void reset() {
        textureRegion = new TextureRegion();
    }
}
