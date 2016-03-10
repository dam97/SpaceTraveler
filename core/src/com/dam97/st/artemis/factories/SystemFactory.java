package com.dam97.st.artemis.factories;

import com.artemis.World;
import com.dam97.st.artemis.components.PlanetarySystemComponent;

/**
 * Created by dam97 on 2016-02-28.
 */
public class SystemFactory {
    World world;

    public SystemFactory(World world) {
        this.world = world;
    }

    public int createSystem(){
        int entity = world.create();
        PlanetarySystemComponent planetarySystemComponent = world.getMapper(PlanetarySystemComponent.class).create(entity);
        return entity;
    }
}
