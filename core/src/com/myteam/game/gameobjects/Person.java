package com.myteam.game.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.myteam.game.help.Builder;

public class Person extends Builder {

    private Body body;
    private Body hand;

    public void compilePerson(){
        body = createPersonBody(BodyDef.BodyType.DynamicBody, new Vector2(70,46), 0.5f,2,64f,0,5);
        //hand = createRectangleBody(BodyDef.BodyType.DynamicBody, new Vector2(350,400), 30,10,1,1);
        RevoluteJointDef bodyHandJoint = new RevoluteJointDef();
        bodyHandJoint.bodyA = body;
        //bodyHandJoint.bodyB = hand;
        //bodyHandJoint.initialize(body,hand,body.getPosition());

        //world.createJoint(bodyHandJoint);

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
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }

    public Body getBody() {
        return body;
    }

    public Body getHand() {
        return hand;
    }
}
