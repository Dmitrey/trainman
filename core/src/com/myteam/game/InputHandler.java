package com.myteam.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class InputHandler implements InputProcessor {

    private GameWorld gameWorld;

    public InputHandler(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public boolean keyDown(int keycode) {//это не клавиша вверх, а нажатие на  какую-то клавишу))
        //dx.app.log("INFO","KEY PRESSED");

        Body body = gameWorld.getPersonBody();

        if (keycode == Input.Keys.RIGHT) {
            GameWorld.rightButtonHold = true;
        }

        if (keycode == Input.Keys.LEFT) {
            GameWorld.leftButtonHold = true;
        }

        if (keycode == Input.Keys.SPACE)
            body.setLinearVelocity(0, 0f);

        if (keycode == Input.Keys.UP)
            body.applyForceToCenter(new Vector2(0,-2000000), true);

//        if (keycode == Input.Keys.DOWN)
//            body.setLinearVelocity(0, 50f);

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.RIGHT) {
            GameWorld.rightButtonHold = false;
        }

        if (keycode == Input.Keys.LEFT) {
            GameWorld.leftButtonHold = false;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        //Gdx.app.log("INFO","KEY PRESSED");
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
