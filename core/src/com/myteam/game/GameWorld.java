package com.myteam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.myteam.game.gameobjects.Person;
import com.myteam.game.help.Builder;

public class GameWorld extends Builder {

    private final Person person;

    final float PIXELS_TO_METERS = 100f;

    public GameWorld() {
        initGameObjects();
        world.setContactListener(new MyContactListener());
        world.setContactFilter(new MyFilter());
        //создание земли
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBodyDef.position.set(0, 0);
        Body groundBody = world.createBody(groundBodyDef);
        EdgeShape edgeShapeGround = new EdgeShape();
        edgeShapeGround.set(0, 60, Gdx.graphics.getWidth() * 5, 60);
        FixtureDef fixtureDefGround = new FixtureDef();
        fixtureDefGround.shape = edgeShapeGround;
        fixtureDefGround.friction = 1f;
        fixtureDefGround.filter.categoryBits = CATEGORY_GROUND;
        groundBody.createFixture(fixtureDefGround);

//        float[] points2 = new float[]{620, 460, 800, 400, 900, 460};
//        PolygonShape polygonShapeExp = new PolygonShape();
//        polygonShapeExp.set(points2);
//        Body ramp2 = createRectangleBodyWithCustomShape(BodyDef.BodyType.StaticBody, 0.5f, 1, polygonShapeExp);
        this.person = new Person();

        for (int i = 5; i < 500; i+=10) {
            createRectangleBody(BodyDef.BodyType.StaticBody,new Vector2(i,60),2,1,0,0,1);
        }
    }

    private void initGameObjects() {

    }

    public void update() {
        person.update();
    }

    public Person getPerson() {
        return person;
    }
}