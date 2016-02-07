package com.dam97.st.listeners;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.Gdx;
import com.dam97.st.components.PlanetComponent;
import com.dam97.st.components.UUIDComponent;

/**
 * Created by dam97 on 2016-02-07.
 */
public class PlanetListener implements EntityListener {
    @Override
    public void entityAdded(Entity entity) {
        Gdx.app.log("APP", "Planet name: " + entity.getComponent(PlanetComponent.class).getName() + "\n Planet ID: " + entity.getComponent(UUIDComponent.class).getUuid().toString());
    }

    @Override
    public void entityRemoved(Entity entity) {

    }
}
