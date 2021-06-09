package com.myteam.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.myteam.game.help.Builder;

public class Person extends Builder {

    public static boolean rightButtonHold = false;
    public static boolean leftButtonHold = false;

    private final Body body;
    private final Body hand;

    public Person(){
        body = createPersonBody(BodyDef.BodyType.DynamicBody, new Vector2(70,46), 0.5f,2,64f,0,5);
        hand = createPersonHand(BodyDef.BodyType.DynamicBody, new Vector2(70,45), 0.25f,1f,64f,0,5);
        RevoluteJointDef bodyHandJoint = new RevoluteJointDef();
        bodyHandJoint.initialize(body,hand,new Vector2(70,46));
        world.createJoint(bodyHandJoint);
    }

    public Body createPersonBody(BodyDef.BodyType bodyType, Vector2 position,
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
        fixtureDef.filter.categoryBits = CATEGORY_PERSON;
//        fixtureDef.filter.maskBits = MASK_PERSON;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }

    public Body createPersonHand(BodyDef.BodyType bodyType, Vector2 position,
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
        fixtureDef.filter.categoryBits = CATEGORY_PERSON;
//        fixtureDef.filter.maskBits = MASK_PERSON;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }

    public void update(){
        world.step(Gdx.graphics.getDeltaTime(), 8, 4);
        if (rightButtonHold) {
            body.applyForceToCenter(new Vector2(20000,0),true);
        }
        if (leftButtonHold) {
            body.applyForceToCenter(new Vector2(-20000,0),true);
        }
        if(body.getLinearVelocity().x > 10f)
            body.setLinearVelocity(10,body.getLinearVelocity().y);
        if(body.getLinearVelocity().x < -10f)
            body.setLinearVelocity(-10,body.getLinearVelocity().y);

        //hand.setTransform(hand.getPosition().x, hand.getPosition().y, 1.5f);
    }

    public Body getBody() {
        return body;
    }

    public Body getHand() {
        return hand;
    }
}