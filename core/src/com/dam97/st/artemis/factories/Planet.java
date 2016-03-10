package com.dam97.st.artemis.factories;

import com.artemis.EntityFactory;
import com.artemis.World;
import com.artemis.annotations.Bind;
import com.artemis.annotations.UseSetter;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.math.Vector3;
import com.dam97.st.artemis.components.NameComponent;
import com.dam97.st.artemis.components.PlanetComponent;
import com.dam97.st.artemis.components.PlanetTypeComponent;
import com.dam97.st.artemis.components.PositionComponent;
import com.dam97.st.artemis.components.SizeComponent;
import com.dam97.st.artemis.entities.PlanetType;

/**
 * Created by dam97 on 2016-02-07.
 */
@Bind({PlanetComponent.class, PositionComponent.class, PlanetTypeComponent.class, SizeComponent.class, NameComponent.class})
public interface Planet extends EntityFactory<Planet> {
    Planet planetComponent();

    Planet planetTypeComponent(PlanetType planetType);

    Planet sizeComponent(float size);

    Planet nameComponent(String name);

    @Bind(PositionComponent.class)
    @UseSetter("setPosition")
    Planet positionComponent(float x, float y, float z);
}
