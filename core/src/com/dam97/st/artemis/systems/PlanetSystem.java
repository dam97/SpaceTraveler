package com.dam97.st.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.Gdx;
import com.dam97.st.artemis.components.NameComponent;
import com.dam97.st.artemis.components.PlanetComponent;
import com.dam97.st.artemis.components.PositionComponent;
import com.dam97.st.artemis.factories.Planet;
import com.dam97.st.utils.DataLoader;

/**
 * Created by dam97 on 2016-02-07.
 */
@Wire
public class PlanetSystem extends IteratingSystem {
    ComponentMapper<NameComponent> nameMapper;
    ComponentMapper<PositionComponent> positionMapper;
    private Planet planet;

    public PlanetSystem() {
        super(Aspect.all(PlanetComponent.class));
    }

    @Override
    protected void initialize() {
        super.initialize();
        planet.nameComponent("Earth").sizeComponent(300).positionComponent(100, 300, 0).create();
    }

    @Override
    public void inserted(IntBag entities) {
        for (int i = 0; i < entities.size(); i++) {
            Gdx.app.log("PlanetSystem", nameMapper.get(entities.get(i)).name);
            Gdx.app.log("PlanetSystem", positionMapper.get(entities.get(i)).position.toString());
        }
        super.inserted(entities);
    }

    @Override
    protected void process(int entity) {
    }
}
