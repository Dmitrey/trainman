package com.myteam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class GameRender {
    private final GameWorld gameWorld;

    private static OrthographicCamera cam;
    private final Box2DDebugRenderer debugRenderer;

    public GameRender(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        debugRenderer = new Box2DDebugRenderer();
    }

    public void render(){

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.position.set(gameWorld.getPerson().getBody().getPosition(), 0);
        cam.update();
        debugRenderer.render(gameWorld.getWorld(), cam.combined);
    }

    public static OrthographicCamera getCam() {
        return cam;
    }
}