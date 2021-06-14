package com.myteam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.myteam.game.gameobjects.FallingObjectsFactory;
import com.myteam.game.gameobjects.Person;
import com.myteam.game.help.Builder;
import com.myteam.game.service.MyContactListener;
import com.myteam.game.service.MyFilter;

public class GameWorld extends Builder {

    private final Person person;
    private final FallingObjectsFactory fallingObjectsFactory;

    final float PIXELS_TO_METERS = 100f;

    public GameWorld() {
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

        person = new Person();
        //new Person(100,46);
        fallingObjectsFactory = new FallingObjectsFactory();

        for (int i = 5; i < 500; i+=10) {
            createRectangleBody(BodyDef.BodyType.StaticBody,new Vector2(i,60),2,1,0,0,1);
        }
    }

    public void update() {
        world.step(Gdx.graphics.getDeltaTime(), 8, 4);
        System.out.println("time " + 1/Gdx.graphics.getDeltaTime());
        person.update();
        fallingObjectsFactory.createFallingObjects();
    }

    public Person getPerson() {
        return person;
    }
}