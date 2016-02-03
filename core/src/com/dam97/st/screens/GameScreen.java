package com.dam97.st.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.dam97.st.SpaceTravelerGame;

/**
 * Created by dam97 on 2016-02-03.
 */
public class GameScreen extends ScreenAdapter{
    SpaceTravelerGame game;

    public GameScreen(SpaceTravelerGame game) {
        super();
        this.game = game;
    }

    @Override
    public void render(float delta) {
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
