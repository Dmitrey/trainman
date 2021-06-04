package com.myteam.game.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public class Person {

    private Body body;
    private Body hand;
    private World world;


    public Person(World world){
        this.world = world;
    }

    public void compilePerson(){
        body = createRectangleBody(BodyDef.BodyType.DynamicBody, new Vector2(300,400), 15,70,1,1);
        hand = createRectangleBody(BodyDef.BodyType.DynamicBody, new Vector2(350,400), 30,10,1,1);
        RevoluteJointDef bodyHandJoint = new RevoluteJointDef();
        bodyHandJoint.bodyA = body;
        bodyHandJoint.bodyB = hand;
        bodyHandJoint.initialize(body,hand,body.getPosition());

        world.createJoint(bodyHandJoint);

    }

    public Body createRectangleBody(BodyDef.BodyType bodyType, Vector2 position,
                                    float hx, float hy, float density, float restitution) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(position);
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(hx, hy);  //здесь задается его размер
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;   //здесь задается его плотность
        fixtureDef.restitution = restitution;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }
}
