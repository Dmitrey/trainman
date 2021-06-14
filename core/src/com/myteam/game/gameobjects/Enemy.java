package com.myteam.game.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.myteam.game.help.Builder;

public class Enemy extends Builder {

    private final Body body;
    private final Body hand;
    private final float spawnX;
    private final float spawnY;
    private boolean goRight = true;

    public Enemy(float posX, float posY){
        spawnX = posX;
        spawnY = posY;
        body = createPersonBody(BodyDef.BodyType.DynamicBody, new Vector2(posX,posY), 0.5f,2,64f,0,5);
        hand = createPersonHand(BodyDef.BodyType.DynamicBody, new Vector2(posX,posY-1), 0.25f,0.75f,0,0,5);
        RevoluteJointDef bodyHandJoint = new RevoluteJointDef();
        bodyHandJoint.initialize(body,hand,new Vector2(posX,posY));
        world.createJoint(bodyHandJoint);
    }

    public void enemyGoLeft(){
        body.applyForceToCenter(new Vector2(-20000,0),true);
    }
    public void enemyGoRight(){
        body.applyForceToCenter(new Vector2(20000,0),true);
    }
    public void enemyJump(){
        body.applyForceToCenter(new Vector2(0, -150000), true);
    }
    public void update(){
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
        if(body.getLinearVelocity().x > 10f)
            body.setLinearVelocity(10,body.getLinearVelocity().y);
        if(body.getLinearVelocity().x < -10f)
            body.setLinearVelocity(-10,body.getLinearVelocity().y);
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
