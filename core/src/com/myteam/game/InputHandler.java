package com.myteam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.myteam.game.gameobjects.BulletFactory;
import com.myteam.game.gameobjects.Person;

public class InputHandler implements InputProcessor {

    private GameWorld gameWorld;
    private Body hand;
    private Body personBody;
    private BulletFactory bulletFactory;

    public static float angle;

    public InputHandler(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        hand = gameWorld.getPerson().getHand();
        personBody = gameWorld.getPerson().getBody();
        bulletFactory = new BulletFactory();
    }

    @Override
    public boolean keyDown(int keycode) {//это не клавиша вверх, а нажатие на  какую-то клавишу))
        //dx.app.log("INFO","KEY PRESSED");

        Body body = gameWorld.getPerson().getBody();

        if (keycode == Input.Keys.RIGHT) {
            Person.rightButtonHold = true;
        }

        if (keycode == Input.Keys.LEFT) {
            Person.leftButtonHold = true;
        }

        if (keycode == Input.Keys.SPACE)
            body.setLinearVelocity(0, 0f);

        if (keycode == Input.Keys.UP && MyContactListener.contactsAmount > 0)
            body.applyForceToCenter(new Vector2(0, -150000), true);

//        if (keycode == Input.Keys.DOWN)
//            body.setLinearVelocity(0, 50f);

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.RIGHT) {
            Person.rightButtonHold = false;
        }

        if (keycode == Input.Keys.LEFT) {
            Person.leftButtonHold = false;
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
        Vector3 sp3 = GameRender.getCam().unproject(new Vector3(screenX, screenY, 0));
        Gdx.app.log("Corrdinates:", " X: " + sp3.x + " Y: " + sp3.y);
        float fromX = gameWorld.getPerson().getHand().getPosition().x;
        float fromY = gameWorld.getPerson().getHand().getPosition().y;
        float velX = sp3.x-fromX;
        float velY = sp3.y-fromY;
        bulletFactory.getBullet(gameWorld.getPerson().getHand().getWorldCenter(),new Vector2(velX*10,velY*10));
        //System.out.println("from: "+fromX+" "+fromY+" to: " + sp3.x + " " + sp3.y);

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
        Vector3 sp3 = GameRender.getCam().unproject(new Vector3(screenX, screenY, 0));
        float pril = sp3.x - personBody.getWorldCenter().x;
        float protiv = sp3.y - personBody.getWorldCenter().y;
        angle = ((float) Math.atan(protiv / pril));
        if (pril < 0)
            angle += 3.14f;
        //System.out.println("angle: " + angle / 3.14f * 180);
        float dX = (float) (Math.cos(angle));
        float dY = (float) (Math.sin(angle));
        hand.setTransform(new Vector2(personBody.getPosition().x + dX, personBody.getPosition().y + dY), angle + 3.12f / 2);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}