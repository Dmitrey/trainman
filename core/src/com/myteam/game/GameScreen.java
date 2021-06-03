package com.myteam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {

    private GameRender gameRender;
    private GameWorld gameWorld;

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
        gameRender.render();
        gameWorld.update();
        //performMove();
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

    void performMove(){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            Gdx.app.log("GAME", "LEFT");
            gameWorld.getRectangle().x--;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            Gdx.app.log("GAME", "RIGHT");
            gameWorld.getRectangle().x++;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            Gdx.app.log("GAME", "UP");
            gameWorld.getRectangle().y--;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            Gdx.app.log("GAME", "DOWN");
            gameWorld.getRectangle().y++;
        }
    }
}
