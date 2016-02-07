package com.dam97.st.screens;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.dam97.st.SpaceTravelerGame;
import com.dam97.st.world.GameWorld;

/**
 * Created by dam97 on 2016-02-03.
 */
public class GameScreen extends ScreenAdapter{
    SpaceTravelerGame game;
    GameWorld gameWorld;
    PooledEngine engine;

    public GameScreen(SpaceTravelerGame game) {
        super();
        this.game = game;
        engine = new PooledEngine();
        gameWorld = new GameWorld(engine);
    }

    @Override
    public void render(float delta) {
        engine.update(delta);
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
