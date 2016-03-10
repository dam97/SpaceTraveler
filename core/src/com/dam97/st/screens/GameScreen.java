package com.dam97.st.screens;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.managers.WorldSerializationManager;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dam97.st.SpaceTravelerGame;
import com.dam97.st.artemis.systems.PlanetSystem;
import com.dam97.st.artemis.systems.RenderingSystem;
import com.dam97.st.artemis.world.GameWorld;

/**
 * Created by dam97 on 2016-02-03.
 */
public class GameScreen extends ScreenAdapter {
    SpaceTravelerGame game;
    GameWorld gameWorld;
    World world;

    public GameScreen(SpaceTravelerGame game) {
        super();
        this.game = game;
        WorldConfiguration worldConfiguration = new WorldConfigurationBuilder()
                .with(
                        new RenderingSystem(new SpriteBatch()),
                        new PlanetSystem())
                .with(new WorldSerializationManager())
                .build();
        world = new World(worldConfiguration);
        gameWorld = new GameWorld(world);
    }

    @Override
    public void render(float delta) {
        world.setDelta(delta);
        world.process();
        super.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
    }
}
