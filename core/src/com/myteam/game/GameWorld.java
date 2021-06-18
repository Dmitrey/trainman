package com.myteam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.myteam.game.gameobjects.Enemy;
import com.myteam.game.gameobjects.FallingObjectsFactory;
import com.myteam.game.gameobjects.Person;
import com.myteam.game.help.Builder;
import com.myteam.game.service.MyContactListener;
import com.myteam.game.service.MyFilter;

public class GameWorld extends Builder {

    private final Person person;
    private FallingObjectsFactory fallingObjectsFactory;
    private final Enemy enemy;
    private final Body groundBody;
    private final Body groundBody2;

    final float PIXELS_TO_METERS = 100f;

    public GameWorld() {
        world.setContactListener(new MyContactListener(this));
        world.setContactFilter(new MyFilter());
        //создание земли
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBodyDef.position.set(0, 0);
        groundBody = world.createBody(groundBodyDef);
        EdgeShape edgeShapeGround = new EdgeShape();
        edgeShapeGround.set(0, 60, Gdx.graphics.getWidth() * 5, 60);
        FixtureDef fixtureDefGround = new FixtureDef();
        fixtureDefGround.shape = edgeShapeGround;
        fixtureDefGround.friction = 1f;
        fixtureDefGround.filter.categoryBits = CATEGORY_GROUND;
        groundBody.createFixture(fixtureDefGround);

        person = new Person();

        //new Person(100,46);
        //fallingObjectsFactory = new FallingObjectsFactory();

        for (int i = 5; i < 100; i+=10) {
            createRectangleBody(BodyDef.BodyType.StaticBody,new Vector2(i,60),2,1,0,0,1);
        }

        enemy = new Enemy(100,46, "enemy1");

        groundBody2 = createRectangleBody(BodyDef.BodyType.StaticBody, new Vector2(70f, 54),10,5,10,0,1);

    }

    public void update() {
        world.step(Gdx.graphics.getDeltaTime(), 8, 8);
        System.out.println("FPS: " + 1/Gdx.graphics.getDeltaTime());
        person.update();
        //fallingObjectsFactory.createFallingObjects();
        enemy.update(person);
        groundBody.setActive(true);
        groundBody2.setActive(true);
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 1, 0, 1);
        shapeRenderer.rect(50, 50, 10, 3);
        shapeRenderer.circle(70, 50, 4);
        shapeRenderer.end();
    }

    public Person getPerson() {
        return person;
    }

    public Enemy getEnemy() {
        return enemy;
    }
}