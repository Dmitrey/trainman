package com.myteam.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.myteam.game.GameRender;
import com.myteam.game.help.Builder;

public class Enemy extends Builder {

    private final Body body;
    private final Body hand;
    private final float spawnX;
    private final float spawnY;
    private final String name;
    private boolean goRight = true;
    private boolean eyeContact = false;
    public boolean haveContact;
    public float enemyTimeNoMove;
    //private float fraction = 1;
    private Vector2 point;
//    private Fixture raycastFixture;

    ShapeRenderer sr = new ShapeRenderer();

    public Enemy(float posX, float posY, String name) {
        this.name = name;
        spawnX = posX;
        spawnY = posY;
        body = createPersonBody(BodyDef.BodyType.DynamicBody, new Vector2(posX, posY), 0.5f, 2, 64f, 0, 1);
        hand = createPersonHand(BodyDef.BodyType.DynamicBody, new Vector2(posX, posY - 1), 0.25f, 0.75f, 0, 0, 1);
        RevoluteJointDef bodyHandJoint = new RevoluteJointDef();
        bodyHandJoint.initialize(body, hand, new Vector2(posX, posY));
        world.createJoint(bodyHandJoint);
    }

    public void enemyGoLeft() {
        if (haveContact)
            body.applyForceToCenter(new Vector2(-20000, 0), true);
    }

    public void enemyGoRight() {
        if (haveContact)
            body.applyForceToCenter(new Vector2(20000, 0), true);
    }

    public void enemyJump() {
        if (haveContact)
            body.applyForceToCenter(new Vector2(0, -150000), true);
    }

    public void update(Person person) {
        //System.out.println("canJump " + haveContact);
        checkEyeContact(person);
        //System.out.println(eyeContact);
        if (eyeContact) {
            pursuit(person);
        } else patrol();
    }

    private void pursuit(Person person) {
        Vector2 pBody = person.getBody().getWorldCenter();
        Vector2 eBody = body.getWorldCenter();

        if (eBody.x > pBody.x + 15) {
            enemyGoLeft();
            enemyTimeNoMove += Gdx.graphics.getDeltaTime();
            if (body.getLinearVelocity().x == 0 && enemyTimeNoMove > 1) {
                enemyJump();
                enemyTimeNoMove = 0;
            }
        }

        if (eBody.x < pBody.x - 15) {
            enemyGoRight();
            enemyTimeNoMove += Gdx.graphics.getDeltaTime();
            if (body.getLinearVelocity().x == 0 && enemyTimeNoMove > 1) {
                enemyJump();
                enemyTimeNoMove = 0;
            }
        }

        if (body.getLinearVelocity().x > 10f)
            body.setLinearVelocity(10, body.getLinearVelocity().y);
        if (body.getLinearVelocity().x < -10f)
            body.setLinearVelocity(-10, body.getLinearVelocity().y);

    }

    public void patrol() {
        float pos = body.getWorldCenter().x;
        if (goRight)
            enemyGoRight();
        else enemyGoLeft();

        if (pos >= spawnX + 10)
            goRight = false;
        if (pos <= spawnX - 10)
            goRight = true;

        if (body.getLinearVelocity().x == 0)
            enemyJump();

        if (body.getLinearVelocity().x > 10f)
            body.setLinearVelocity(10, body.getLinearVelocity().y);
        if (body.getLinearVelocity().x < -10f)
            body.setLinearVelocity(-10, body.getLinearVelocity().y);
    }

    private void checkEyeContact(Person person) {

        RayCastCallback rayCastCallback = (fixture, point, normal, fraction) -> {
//            if (this.fraction >= fraction){
//                this.fraction = fraction;
//                this.point = point;
//                raycastFixture = fixture;

                //System.out.println("Fraction: "+ fraction + " fixture: " + fixture.getFilterData().categoryBits);
//            }
            this.point = point;
            eyeContact = fixture.getFilterData().categoryBits == CATEGORY_PERSON;
            return fraction;
        };

        world.rayCast(rayCastCallback, body.getWorldCenter(), new Vector2(person.getBody().getWorldCenter().x,person.getBody().getWorldCenter().y));
        sr.begin(ShapeRenderer.ShapeType.Line);
        Vector3 vBody = GameRender.getCam().project(new Vector3(body.getPosition().x, body.getPosition().y, 0));
        Vector3 vPoint = GameRender.getCam().project(new Vector3(this.point.x, this.point.y, 0));
        sr.line(vBody.x, vBody.y, vPoint.x, vPoint.y);
        sr.end();
        System.out.println(eyeContact);
    }

    private Body createPersonBody(BodyDef.BodyType bodyType, Vector2 position,
                                  float hx, float hy, float density, float restitution, float friction) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(position);
        bodyDef.fixedRotation = true;
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(hx, hy);  //здесь задается его размер
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;   //здесь задается его плотность
        fixtureDef.restitution = restitution;
        fixtureDef.friction = friction;
        fixtureDef.filter.categoryBits = CATEGORY_ENEMY;
        body.createFixture(fixtureDef);
        body.setUserData(name);
        shape.dispose();
        return body;
    }

    private Body createPersonHand(BodyDef.BodyType bodyType, Vector2 position,
                                  float hx, float hy, float density, float restitution, float friction) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(position);
        //bodyDef.position.angle(new Vector2(20,20));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(hx, hy);  //здесь задается его размер
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;   //здесь задается его плотность
        fixtureDef.restitution = restitution;
        fixtureDef.friction = friction;
        fixtureDef.filter.categoryBits = CATEGORY_ENEMY;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }
}
