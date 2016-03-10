package com.dam97.st.world;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.utils.Json;
import com.dam97.st.components.PlanetComponent;
import com.dam97.st.components.UUIDComponent;
import com.dam97.st.entities.PlanetType;

/**
 * Created by dam97 on 2016-02-07.
 */
public class PlanetFactory {
    PooledEngine engine;

    public PlanetFactory(PooledEngine engine) {
        this.engine = engine;
    }

    public Entity createPlanet(String name, float size, PlanetType type){
        Entity planet = engine.createEntity();
        PlanetComponent planetComponent = engine.createComponent(PlanetComponent.class);
        // Unique ID for planet
        UUIDComponent uuidComponent = engine.createComponent(UUIDComponent.class);
        planetComponent.setName(name);
        planetComponent.setSize(size);
        planetComponent.setType(type);
        planet.add(planetComponent);
        planet.add(uuidComponent);
        return planet;
    }

    public Entity loadPlanet(String jsonString){
        Json json = new Json();
        return json.fromJson(Entity.class, jsonString);
    }
}
