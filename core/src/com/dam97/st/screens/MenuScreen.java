package com.dam97.st.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.dam97.st.SpaceTravelerGame;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

/**
 * Created by dam97 on 2016-02-20.
 */
public class MenuScreen extends ScreenAdapter {
    SpaceTravelerGame game;
    Stage menuStage;

    VisTable table;
    VisTextButton startButton;

    public MenuScreen(SpaceTravelerGame game) {
        super();
        this.game = game;
        VisUI.load();

        this.menuStage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(menuStage);

        table = new VisTable(true);
        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        startButton = new VisTextButton("Start");
        startButton.align(Align.center);
        startButton.setName("startButton");
        startButton.addListener(new ChangeListener() {
            GameScreen gameScreen;

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameScreen = new GameScreen((SpaceTravelerGame) Gdx.app.getApplicationListener());
                ((SpaceTravelerGame) Gdx.app.getApplicationListener()).setScreen(gameScreen);
                menuStage.dispose();
            }
        });

        table.add(startButton).size(200,100);
        menuStage.addActor(table);

    }

    @Override
    public void render(float delta) {
        menuStage.act(delta);
        menuStage.draw();
        super.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        this.menuStage.getViewport().update(width, height, true);
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        this.menuStage.dispose();
        super.dispose();
    }

}
