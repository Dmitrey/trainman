package com.myteam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class GameRender {
    private final GameWorld gameWorld;

    private static OrthographicCamera cam;
    private final Box2DDebugRenderer debugRenderer;
    private final SpriteBatch batcher;
    private final Texture texture;

    public GameRender(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, Gdx.graphics.getWidth()/20, Gdx.graphics.getHeight()/20);
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        debugRenderer = new Box2DDebugRenderer();
        batcher = new SpriteBatch();
        texture = new Texture("badlogic.jpg");
    }

    public void render(){

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.position.set(gameWorld.getPerson().getBody().getPosition(), 0);
        cam.update();
        debugRenderer.render(gameWorld.getWorld(), cam.combined);


        Body personBody = gameWorld.getPerson().getBody();
        Vector3 personPositionWorld = cam.project(new Vector3(personBody.getPosition().x,personBody.getPosition().y,0));
        batcher.begin();
        batcher.draw(texture,personPositionWorld.x-15,personPositionWorld.y-45,25f,90);
        batcher.end();

    }

    public static OrthographicCamera getCam() {
        return cam;
    }
}