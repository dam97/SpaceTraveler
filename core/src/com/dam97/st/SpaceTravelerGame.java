package com.dam97.st;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dam97.st.screens.GameScreen;
import com.dam97.st.screens.MenuScreen;

public class SpaceTravelerGame extends Game {
	MenuScreen menuScreen;
	SpriteBatch batch;
	
	@Override
	public void create () {
		Assets.load();
		batch = new SpriteBatch();
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}
}
