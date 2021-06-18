package com.myteam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.myteam.game.service.InputHandler;

public class GameScreen implements Screen {

    private GameRender gameRender;
    private GameWorld gameWorld;
    public static boolean paused;

    public GameScreen() {
        gameWorld = new GameWorld();
        gameRender = new GameRender(gameWorld);
        Gdx.input.setInputProcessor(new InputHandler(gameWorld));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Gdx.app.log("Runtime", "helb");
        if (!paused) {
            gameRender.render();
            gameWorld.update();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
