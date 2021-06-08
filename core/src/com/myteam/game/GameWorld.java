package com.myteam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.myteam.game.gameobjects.Person;
import com.myteam.game.help.Builder;

public class GameWorld extends Builder {

    private Body carBody;
    private Body backWheel;
    private Body frontWheel;
    private Body groundBody;
    private Body personBody;

    static boolean rightButtonHold = false;
    static boolean leftButtonHold = false;

    final float PIXELS_TO_METERS = 100f;

    public GameWorld() {
        initGameObjects();
        world.setContactListener(new MyContactListener());
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

        float[] points2 = new float[]{620, 460, 800, 400, 900, 460};
        PolygonShape polygonShapeExp = new PolygonShape();
        polygonShapeExp.set(points2);
        Body ramp2 = createRectangleBodyWithCustomShape(BodyDef.BodyType.StaticBody, 0.5f, 1, polygonShapeExp);
        Person person = new Person();
        person.compilePerson();
        personBody = person.getBody();

        for (int i = 5; i < 500; i+=10) {
            createRectangleBody(BodyDef.BodyType.StaticBody,new Vector2(i,60),2,1,0,0,1);
        }
    }

    public Body getBody() {
        return carBody;
    }

    private void initGameObjects() {

    }

    public void update() {

        world.step(Gdx.graphics.getDeltaTime(), 8, 4);
        if (rightButtonHold) {
            personBody.applyForceToCenter(new Vector2(20000,0),true);
        }

        if (leftButtonHold) {
            personBody.applyForceToCenter(new Vector2(-20000,0),true);
        }

        if(personBody.getLinearVelocity().x > 10f)
            personBody.setLinearVelocity(10,personBody.getLinearVelocity().y);
        if(personBody.getLinearVelocity().x < -10f)
            personBody.setLinearVelocity(-10,personBody.getLinearVelocity().y);

        //Gdx.app.log("speed", String.valueOf(personBody.getLinearVelocity().x));

        //car controls
//        if (rightButtonHold) {
//            frontWheel.applyTorque(1000_000_00, true);
//            backWheel.applyTorque(1000_000_00, true);
//        }
//
//        if (leftButtonHold) {
//            frontWheel.applyTorque(-1000_000_00, true);
//            backWheel.applyTorque(-1000_000_00, true);
//        }
//
//        if (backWheel.getAngularVelocity() > 50)
//            backWheel.setAngularVelocity(50);
//        if (backWheel.getAngularVelocity() < -50)
//            backWheel.setAngularVelocity(-50);
//        if (frontWheel.getAngularVelocity() > 50)
//            frontWheel.setAngularVelocity(50);
//        if (frontWheel.getAngularVelocity() < -50)
//            frontWheel.setAngularVelocity(-50);
    }

    public Body getBackWheel() {
        return backWheel;
    }

    public Body getFrontWheel() {
        return frontWheel;
    }

    public Body getCarBody() {
        return carBody;
    }

    public Body getPersonBody() {
        return personBody;
    }
}