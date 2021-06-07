package com.myteam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class GameRender {
    private GameWorld gameWorld;

    private ShapeRenderer shapeRenderer;
    private OrthographicCamera cam;

    private Box2DDebugRenderer debugRenderer;

    public GameRender(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        debugRenderer = new Box2DDebugRenderer();
    }

    public void render(){

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.position.set(gameWorld.getPersonBody().getPosition().x, gameWorld.getPersonBody().getPosition().y, 0);
        cam.update();

        //Gdx.app.log("Render","is working");

//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(Color.CORAL);
//        shapeRenderer.rect(gameWorld.getRectangle().x, gameWorld.getRectangle().y, gameWorld.getRectangle().width,
//               gameWorld.getRectangle().height);
//
//        shapeRenderer.setColor(Color.CYAN);
//        shapeRenderer.circle(gameWorld.getCircle().x, gameWorld.getCircle().y, gameWorld.getCircle().radius);
//
//        shapeRenderer.end();

        debugRenderer.render(gameWorld.getWorld(), cam.combined);
    }
}